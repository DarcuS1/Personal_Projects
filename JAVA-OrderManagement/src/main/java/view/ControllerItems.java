package view;

import buss.ItemBLL;
import com.example.demo1.HelloApplication;
import data.ItemDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Items;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerItems implements Initializable {

    //region declarations for users
    private Label label;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField itemIDField;
    @FXML
    TextField itemNameField;
    @FXML
    TextField itemPriceField;
    @FXML
    TextField itemStockField;

    @FXML
    Button showDataItemBtn;

    @FXML
    Button addItemBtn;

    @FXML
    Button updateItemBtn;

    @FXML
    Button deleteItemBtn;

    @FXML
    private TableView<Items> itemTable;

    @FXML
    private TableColumn<Items, Integer> itemID;
    @FXML
    private TableColumn<Items, String> itemName;
    @FXML
    private TableColumn<Items, Double> itemPrice;
    @FXML
    private TableColumn<Items, Integer> itemStock;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void showDataItems(MouseEvent event) {
        try {
            itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
            itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
            itemStock.setCellValueFactory(new PropertyValueFactory<>("itemStock"));


            ItemBLL itemBLL = new ItemBLL();
            List<Items> itemList = itemBLL.getAllItems();

            if (itemList == null) {
                System.out.println("CACAAAAAAAAAA");
            }
            itemTable.getItems().clear();


            for (Items item1 : itemList) {
                int itemid = item1.getItemID();
                String itemname = item1.getItemName();
                Double itemprice = item1.getItemPrice();
                int itemstock = item1.getItemStock();

                Items newitem = new Items(itemid, itemname, itemprice, itemstock);

                ObservableList<Items> newitems = itemTable.getItems();
                newitems.add(newitem);
                itemTable.setItems(newitems);
            }


        } catch (Exception e) {
            // Handle any exceptions or display an error message
            givePopUp(event, e.getMessage());
        }
    }


    @FXML
    public void handleClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleBack(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleAddItems(MouseEvent event) throws IOException {
        try {
            int id = Integer.parseInt(itemIDField.getText());
            String name = itemNameField.getText();
            double price = Double.parseDouble(itemPriceField.getText());
            int stock = Integer.parseInt(itemStockField.getText());

            Items newItem = new Items(id, name, price, stock);

            ItemBLL itemBLL = new ItemBLL();
            Items insertedItem = itemBLL.insertNewItems(newItem);
            showDataItems(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }

        // Update UI or display success message
    }

    @FXML
    public void handleUpdateItems(MouseEvent event) {
        try {
            int id = Integer.parseInt(itemIDField.getText());
            String name = itemNameField.getText();
            double price = Double.parseDouble(itemPriceField.getText());
            int stock = Integer.parseInt(itemStockField.getText());

            Items updatedItem = new Items(id, name, price, stock);

            ItemBLL itemBLL = new ItemBLL();
            boolean updateResult = itemBLL.updateNewItem(id, updatedItem);

            // Update UI or display success message based on updateResult
            showDataItems(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }

    @FXML
    public void handleDeleteItems(MouseEvent event) {
        try {
            int id = Integer.parseInt(itemIDField.getText());

            ItemBLL itemBLL = new ItemBLL();
            boolean deleteResult = itemBLL.deleteItem(id);

            // Update UI or display success message based on deleteResult
            showDataItems(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }


    private void givePopUp(MouseEvent event, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(((Node) event.getSource()).getScene().getWindow());
        alert.showAndWait();
    }


    //endregion
}
