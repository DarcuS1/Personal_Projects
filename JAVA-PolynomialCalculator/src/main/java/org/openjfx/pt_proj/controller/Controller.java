/**
 * @author SAMIJ
 */

package org.openjfx.pt_proj.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import org.openjfx.pt_proj.org.Polynomial;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    @FXML
    private TextField poly1;
    @FXML
    private TextField poly2;
    @FXML
    private TextField result;

    public void handleClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void plus(MouseEvent event) {
        try {
            Polynomial polyinom1 = new Polynomial(poly1.getText());
            Polynomial polyinom2 = new Polynomial(poly2.getText());
            Polynomial sum = polyinom1.add(polyinom2);
            result.setText("Sum: " + sum);
        }catch (Exception e)
        {
            result.setText("exception");
        }
    }

    @FXML
    public void minus(MouseEvent event) {
        try{
            Polynomial polyinom1 = new Polynomial(poly1.getText());
            Polynomial polyinom2 = new Polynomial(poly2.getText());
        Polynomial difference = polyinom1.subtract(polyinom2);
        result.setText("Difference: " + difference);
        }catch (Exception e)
        {
            result.setText("exception");
        }
    }
    @FXML
    public void times(MouseEvent event) {
        try{
        Polynomial polyinom1 = new Polynomial(poly1.getText());
        Polynomial polyinom2 = new Polynomial(poly2.getText());
        Polynomial product = polyinom1.multiply(polyinom2);
        result.setText("Product: " + product);
        }catch (Exception e)
        {
            result.setText("exception");
        }
    }

    @FXML
    public void divided(MouseEvent event) {
        try{
        Polynomial polyinom1 = new Polynomial(poly1.getText());
        Polynomial polyinom2 = new Polynomial(poly2.getText());
        Pair<Polynomial, Polynomial> quotientAndRemainder = polyinom1.divide(polyinom2);
        if(quotientAndRemainder==null){
            result.setText("exception");
        }
        result.setText("Quotient: " + quotientAndRemainder.getKey() + " " + "Remainder: " + quotientAndRemainder.getValue());
        }catch (Exception e)
        {
            result.setText("exception");
        }
    }

    @FXML
    public void differential(MouseEvent event) {
        try{
        Polynomial polyinom1 = new Polynomial(poly1.getText());
        Polynomial derivative = polyinom1.differentiate();
        result.setText("Derivative: " + derivative);
        }catch (Exception e)
        {
            result.setText("exception");
        }
    }

    @FXML
    public void integral(MouseEvent event) {
        try{
        Polynomial polyinom1 = new Polynomial(poly1.getText());
        Polynomial integral = polyinom1.integrate();
        result.setText("Integral: " + integral);
        }catch (Exception e)
        {
            result.setText("exception");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
