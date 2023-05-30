package test;

import javafx.util.Pair;
import org.openjfx.pt_proj.org.Polynomial;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


class PolynomialTest {


    @Test
    static void add() {
        Map<Integer, Double> monomials1 = new HashMap<>();
        monomials1.put(2, 3.0);
        monomials1.put(1, 4.0);
        monomials1.put(0, 1.0);
        Polynomial poly1 = new Polynomial(monomials1);

        Map<Integer, Double> monomials2 = new HashMap<>();
        monomials2.put(2, 1.0);
        monomials2.put(1, 2.0);
        monomials2.put(0, 3.0);
        Polynomial poly2 = new Polynomial(monomials2);

        // Act
        Polynomial result = poly1.add(poly2);

        // Assert
        Map<Integer, Double> expectedMonomials = new HashMap<>();
        expectedMonomials.put(2, 4.0);
        expectedMonomials.put(1, 6.0);
        expectedMonomials.put(0, 4.0);
        Polynomial expected = new Polynomial(expectedMonomials);
        Assert.assertEquals(expected, result);

        Map<Integer, Double> monomials12 = new HashMap<>();
        monomials12.put(7, 9.0);
        monomials12.put(5, 2.0);
        monomials12.put(0, 1.0);
        Polynomial poly12 = new Polynomial(monomials12);

        Map<Integer, Double> monomials22 = new HashMap<>();
        monomials22.put(6, 1.0);
        monomials22.put(4, 2.0);
        monomials22.put(3, 3.0);
        Polynomial poly22 = new Polynomial(monomials22);

        // Act
        Polynomial result22 = poly12.add(poly22);

        // Assert
        Map<Integer, Double> expectedMonomials2= new HashMap<>();
        expectedMonomials2.put(7, 9.0);
        expectedMonomials2.put(6, 1.0);
        expectedMonomials2.put(5, 2.0);
        expectedMonomials2.put(4, 2.0);
        expectedMonomials2.put(3, 3.0);
        expectedMonomials2.put(0, 1.0);
        Polynomial expected22 = new Polynomial(expectedMonomials2);
        Assert.assertEquals(expected22, result22);



    }

    @Test
    static void subtract() {
        Map<Integer, Double> monomials1 = new HashMap<>();
        monomials1.put(2, 3.0);
        monomials1.put(1, 4.0);
        monomials1.put(0, 1.0);
        Polynomial poly11 = new Polynomial(monomials1);

        Map<Integer, Double> monomials2 = new HashMap<>();
        monomials2.put(2, 1.0);
        monomials2.put(1, 2.0);
        monomials2.put(0, 3.0);
        Polynomial poly21 = new Polynomial(monomials2);

        // Act
        Polynomial result1 = poly11.subtract(poly21);

        // Assert
        Map<Integer, Double> expectedMonomials = new HashMap<>();
        expectedMonomials.put(2, 2.0);
        expectedMonomials.put(1, 2.0);
        expectedMonomials.put(0, -2.0);
        Polynomial expected = new Polynomial(expectedMonomials);
        Assert.assertEquals(result1, expected);
        // Test subtracting a polynomial with a smaller degree
        Polynomial poly1 = new Polynomial(Map.of(3, 1.0, 2, 2.0, 1, 3.0, 0, 4.0));
        Polynomial poly2 = new Polynomial(Map.of(1, 1.0, 0, 1.0));
        Polynomial result = poly1.subtract(poly2);
        Assert.assertEquals(new Polynomial(Map.of(3, 1.0, 2, 2.0, 1, 2.0, 0, 3.0)), result);

        // Test subtracting a polynomial with a larger degree
        poly1 = new Polynomial(Map.of(1, 1.0, 0, 1.0));
        poly2 = new Polynomial(Map.of(3, 1.0, 2, 2.0, 1, 3.0, 0, 4.0));
        result = poly1.subtract(poly2);
        Assert.assertEquals(new Polynomial(Map.of(3, -1.0, 2, -2.0, 1, -2.0, 0, -3.0)), result);

        // Test subtracting a polynomial with the same degree
        poly1 = new Polynomial(Map.of(2, 1.0, 1, 2.0, 0, 3.0));
        poly2 = new Polynomial(Map.of(2, 1.0, 1, 1.0, 0, 2.0));
        result = poly1.subtract(poly2);
        Assert.assertEquals(new Polynomial(Map.of(1, 1.0, 0, 1.0)), result);

        // Test subtracting a polynomial from itself
        poly1 = new Polynomial(Map.of(3, 1.0, 1, 2.0, 0, 3.0));
        result = poly1.subtract(poly1);
        Assert.assertEquals(new Polynomial(Map.of()), result);

        // Test subtracting an empty polynomial
        poly1 = new Polynomial(Map.of(2, 1.0, 1, 2.0, 0, 3.0));
        poly2 = new Polynomial(Map.of());
        result = poly1.subtract(poly2);
        Assert. assertEquals(poly1, result);
    }

    @Test
    static void multiply() {
        Map<Integer, Double> monomials1 = new HashMap<>();
        monomials1.put(2, 3.0);
        monomials1.put(1, 4.0);
        Polynomial poly1 = new Polynomial(monomials1);



        Map<Integer, Double> monomials2 = new HashMap<>();
        monomials2.put(1, 2.0);
        monomials2.put(0, 3.0);
        Polynomial poly2 = new Polynomial(monomials2);


        // Act
        Polynomial result = poly1.multiply(poly2);


        // Assert
        Map<Integer, Double> expectedMonomials = new HashMap<>();

        expectedMonomials.put(3, 6.0);
        expectedMonomials.put(2, 17.0);
        expectedMonomials.put(1, 12.0);
        Polynomial expectedResult=new Polynomial(expectedMonomials);


        Assert.assertEquals(result, expectedResult);
    }

    @Test
    static void divide() {
        // Test a simple case where there is no remainder
        Polynomial dividend = new Polynomial(Map.of(2, 3.0, 1, 2.0, 0, 1.0));
        Polynomial divisor = new Polynomial(Map.of(1, 1.0, 0, 1.0));
        Pair<Polynomial, Polynomial> result = dividend.divide(divisor);
        Polynomial quotient = result.getKey();
        Polynomial remainder = result.getValue();
        Assert.assertEquals(new Polynomial(Map.of(1, 3.0, 0, -1.0)), quotient);
        Assert.assertEquals(new Polynomial(Map.of(0,2.0)), remainder);

        // Test a case where there is a non-zero remainder
        dividend = new Polynomial(Map.of(3, 1.0, 2, -2.0, 1, -1.0, 0, 2.0));
        divisor = new Polynomial(Map.of(1, 1.0, 0, 1.0));
        result = dividend.divide(divisor);
        quotient = result.getKey();
        remainder = result.getValue();
        Assert.assertEquals(new Polynomial(Map.of(2, 1.0, 1, -3.0,0,2.0)), quotient);
        Assert.assertEquals(new Polynomial(Map.of()), remainder);

        // Test a case where the dividend is smaller than the divisor
        dividend = new Polynomial(Map.of(1, 1.0, 0, 1.0));
        divisor = new Polynomial(Map.of(2, 1.0, 0, 1.0));
        result = dividend.divide(divisor);
        quotient = result.getKey();
        remainder = result.getValue();
        Assert.assertEquals(new Polynomial(Map.of()), quotient);
        Assert.assertEquals(dividend, remainder);

        // Test a case where the divisor is zero
        dividend = new Polynomial(Map.of(1, 1.0, 0, 1.0));
        divisor = new Polynomial(Map.of());
        try {
            result = dividend.divide(divisor);
            Assert.fail("Expected an ArithmeticException to be thrown");
        } catch (ArithmeticException e) {
            Assert.assertEquals(null, e.getMessage());
        }
    }

    @Test
    static void differentiate() {
        Map<Integer, Double> monomials2 = new HashMap<>();
        monomials2.put(1, 2.0);
        monomials2.put(0, 3.0);
        Polynomial poly2 = new Polynomial(monomials2);

        // Act
        Polynomial result = poly2.differentiate();

        // Assert
        Map<Integer, Double> expectedMonomials = new HashMap<>();
        expectedMonomials.put(0, 2.0);


        Polynomial expected = new Polynomial(expectedMonomials);
        Assert.assertEquals(result, expected);
    }

    @Test
    static void integrate() {
        Map<Integer, Double> monomials2 = new HashMap<>();
        monomials2.put(1, 2.0);
        monomials2.put(0, 3.0);
        Polynomial poly2 = new Polynomial(monomials2);

        // Act
        Polynomial result = poly2.integrate();


        // Assert
        Map<Integer, Double> expectedMonomials = new HashMap<>();
        expectedMonomials.put(2, 1.0);
        expectedMonomials.put(1, 3.0);

        Polynomial expected = new Polynomial(expectedMonomials);

        Assert.assertEquals(result, expected);
    }


    public static void main(String[] args) {
        add();
        subtract();
        multiply();
        divide();
        differentiate();
        integrate();

    }




}