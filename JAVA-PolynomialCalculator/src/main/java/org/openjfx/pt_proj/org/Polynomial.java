/**
 * @author SAMIJ
 */

package org.openjfx.pt_proj.org;

import javafx.util.Pair;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private  Map<Integer, Double> monomi;
    private static final DecimalFormat decFor = new DecimalFormat("0.00");
    private static final Pattern POLYNOMIAL_REGEX = Pattern.compile("([+-]?(?:(?:\\d*x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Polynomial)) {
            return false;
        }
        Polynomial other = (Polynomial) obj;
        return Objects.equals(monomi, other.monomi);
    }
    public Polynomial(Map<Integer, Double> monomials) {
        this.monomi = monomials;
    }

    public Polynomial(String expr) throws Exception {
        expr = expr.toLowerCase().replaceAll("\\*", "").replaceAll("\\s", "");

        Matcher matcher = POLYNOMIAL_REGEX.matcher(expr);

        monomi = new HashMap<>();

        while (matcher.find()) {
            String group = matcher.group(1);
            String coeffString = "1";
            String expString = "1";

            try {
                if (group.contains("x")) {
                    int xIndex = group.indexOf('x');
                    if (xIndex > 0) {
                        coeffString = group.substring(0, xIndex);
                    }

                    int pIndex = group.indexOf("^");
                    if (pIndex != -1) {
                        expString = group.substring(pIndex + 1);
                    }
                } else {
                    coeffString = group;
                    expString = "0";
                }

                Double coeff = Double.parseDouble(coeffString.equals("-") ? "-1" : coeffString.equals("+") ? "1" : coeffString);
                Integer power = Integer.parseInt(expString);

                monomi.put(power, monomi.getOrDefault(power, 0.0) + coeff);
            } catch (Exception ex) {
                throw new Exception("Failed to parse group: " + group);
            }
        }
    }


    public Polynomial add(Polynomial polyToAdd) {
        Map<Integer, Double> resPolY = new HashMap<>(monomi);
        for (Map.Entry<Integer, Double> entry : polyToAdd.monomi.entrySet()) {
            int exponent = entry.getKey();
            Double coefficient = entry.getValue();
            Double currentCoefficient = resPolY.getOrDefault(exponent, 0.0);
            resPolY.put(exponent, currentCoefficient + coefficient);
        }
        return new Polynomial(resPolY);
    }

    public Polynomial subtract(Polynomial other) {
        Map<Integer, Double> coeff = new HashMap<Integer, Double>(monomi);
        for(Map.Entry<Integer, Double> entry : other.monomi.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();

            Double newVal = coeff.getOrDefault(key, 0.0) - value;
            coeff.put(key, newVal);
            if(Math.abs(newVal) < 0.001 ) {
                coeff.remove(key);
            }
        }

        return new Polynomial(coeff);
    }

    public Polynomial multiply(Polynomial polyToMul) {
        Map<Integer, Double> resPolY = new HashMap<>();
        for (Map.Entry<Integer, Double> entry1 : monomi.entrySet()) {
            int exponent1 = entry1.getKey();
            Double coefficient1 = entry1.getValue();
            for (Map.Entry<Integer, Double> entry2 : polyToMul.monomi.entrySet()) {
                int exponent2 = entry2.getKey();
                Double coefficient2 = entry2.getValue();
                int exponent = exponent1 + exponent2;
                Double coefficient = coefficient1 * coefficient2;
                Double currentCoefficient = resPolY.getOrDefault(exponent, 0.0);
                resPolY.put(exponent, currentCoefficient + coefficient);
            }
        }
        return new Polynomial(resPolY);
    }

    public Pair<Polynomial, Polynomial> divide(Polynomial polyToDiv) {
        try {
            Map<Integer, Double> quotientPolY = new HashMap<>();
            Map<Integer, Double> remainderPolY = new HashMap<>(this.monomi);

            int key;
            key=0;
            int ok=0;
            for (Map.Entry<Integer, Double> entry2 : polyToDiv.monomi.entrySet()) {

                Double zeroDET=entry2.getValue();
                key++;
                if((zeroDET == 0.0) ){
                    ok=1;
                }
            }
            if(ok==1 && key==1){
                return null;
            }
            int divisorDegree = Collections.max(polyToDiv.monomi.keySet());
            while (remainderPolY.size() > 0 && Collections.max(remainderPolY.keySet()) >= divisorDegree) {
                int leadRemainderDegree = Collections.max(remainderPolY.keySet());
                Double leadDivisorCoeff = polyToDiv.monomi.get(divisorDegree);
                Double leadRemainderCoeff = remainderPolY.get(leadRemainderDegree);
                int quotientDegree = leadRemainderDegree - divisorDegree;
                Double quotientCoeff = leadRemainderCoeff / leadDivisorCoeff;
                quotientPolY.put(quotientDegree, Double.valueOf(decFor.format(quotientCoeff)));
                for (int i = 0; i <= divisorDegree; i++) {
                    int currDegree = quotientDegree + i;
                    Double currRemainderCoeff = remainderPolY.getOrDefault(currDegree, 0.0);
                    Double currDivisorCoeff = polyToDiv.monomi.getOrDefault(i, 0.0);
                    Double newRemainderCoeff = currRemainderCoeff - quotientCoeff * currDivisorCoeff;
                    if (newRemainderCoeff == 0) {
                        remainderPolY.remove(currDegree);
                    } else {
                        remainderPolY.put(currDegree, Double.valueOf(decFor.format(newRemainderCoeff)));
                    }
                }
            }
            Polynomial quotient = new Polynomial(quotientPolY);
            Polynomial remainder = new Polynomial(remainderPolY);
            return new Pair<>(quotient, remainder);
        }catch(Exception e)
        {
            throw new ArithmeticException();
        }
    }

    public Polynomial differentiate() {
        Map<Integer, Double> resPolY = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : monomi.entrySet()) {
            int exponent = entry.getKey();
            Double coefficient = entry.getValue();
            if (exponent > 0) {
                resPolY.put(exponent - 1, exponent * coefficient);
            }
            if (exponent == 0) {
                resPolY.put(0, 0.0);
            }
        }
        return new Polynomial(resPolY);
    }

    public Polynomial integrate() {
        Map<Integer, Double> resPolY = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : monomi.entrySet()) {
            int exponent = entry.getKey();
            Double coefficient = entry.getValue();
            if (exponent == 0) {
                resPolY.put(1, coefficient);
            } else {
                resPolY.put(exponent + 1, Double.valueOf(decFor.format(coefficient / (exponent + 1))));
            }
        }
        return new Polynomial(resPolY);
    }

    @Override
    public String toString() {
        StringBuilder outPloy = new StringBuilder();
        for (Map.Entry<Integer, Double> entry : monomi.entrySet()) {
            int exponent = entry.getKey();
            Double coefficient = entry.getValue();
            if (exponent == 0) {
                outPloy.append(coefficient);
            } else if (exponent == 1) {
                if (coefficient == 1) {
                    outPloy.append("x");
                } else if (coefficient == -1) {
                    outPloy.append("-x");
                } else {
                    outPloy.append(coefficient).append("x");
                }
            } else if (coefficient == 1) {
                outPloy.append("x^").append(exponent);
            } else if (coefficient == -1) {
                outPloy.append("-x^").append(exponent);
            } else {
                outPloy.append(coefficient).append("x^").append(exponent);
            }
            outPloy.append(" + ");
        }
        if (outPloy.length() > 0) {
            outPloy.delete(outPloy.length() - 3, outPloy.length());
        }
        return outPloy.toString();
    }
}
