package model;

import data.DataConnection;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public record Bill(int orderId, String userName, String itemName, double totalPrice, String date) {


    public void insertIntoLogTable() {
        try (Connection connection = DataConnection.upConn();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO billlog (orderid, username, itemname, totalprice, date) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, orderId);
            statement.setString(2, userName);
            statement.setString(3, itemName);
            statement.setDouble(4, totalPrice);
            statement.setString(5, date);
            statement.executeUpdate();
        } catch (SQLException e) {
            givePopUp(e.getMessage());
        }
    }

    private void givePopUp(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
