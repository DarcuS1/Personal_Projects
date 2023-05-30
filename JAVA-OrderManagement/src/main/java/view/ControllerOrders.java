package view;

import buss.ItemBLL;
import buss.OrderBLL;
import com.example.demo1.HelloApplication;
import data.ItemDAO;
import data.UserDAO;
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
import model.Bill;
import model.Items;
import model.Orders;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Controller class for managing orders view.
 */
public class ControllerOrders implements Initializable {

    @FXML
    private TableView<Orders> ordersTable;

    @FXML
    private TableColumn<Orders, Integer> orderIDT;
    @FXML
    private TableColumn<Orders, Integer> userIDT;
    @FXML
    private TableColumn<Orders, Integer> itemIDT;
    @FXML
    private TableColumn<Orders, Integer> quantityT;

    @FXML
    private TextField orderIDField;

    @FXML
    private TextField quantityField;

    @FXML
    private ComboBox<Integer> userIDCombo;

    @FXML

    private ComboBox<Integer> itemIDCombo;

    private OrderBLL orderBLL = new OrderBLL();
    private UserDAO userDAO = new UserDAO();
    private ItemDAO itemDAO = new ItemDAO();
    /**
     * Initializes the controller.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();

    }
    /**
     * Initializes the combo boxes with user and item data from the database.
     */
    private void initializeComboBoxes() {
        // Get the list of users from the database
        List<Users> userList = userDAO.getAllUsers();
        ObservableList<Users> users = FXCollections.observableArrayList(userList);
        List<Integer> usersIds = new ArrayList<>();
        for (Users user : users) {
            usersIds.add(user.getUserID());
        }
        // Set the user list in the combo box
        ObservableList<Integer> userIdsObservableList = FXCollections.observableArrayList(usersIds);
        userIDCombo.setItems(userIdsObservableList);

        // Get the list of items from the database
        List<Items> itemList = itemDAO.getAllItems();
        ObservableList<Items> items = FXCollections.observableArrayList(itemList);
        List<Integer> itemIds = new ArrayList<>();
        for (Items item : items) {
            itemIds.add(item.getItemID());
        }
        // Set the item list in the combo box
        ObservableList<Integer> itemIdsObservableList = FXCollections.observableArrayList(itemIds);
        itemIDCombo.setItems(itemIdsObservableList);
    }

    /**
     * Retrieves and displays the order data in the table view.
     *
     * @param event The MouseEvent that triggered the action.
     */
    @FXML
    public void showDataOrders(MouseEvent event) {
        try {
            orderIDT.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            userIDT.setCellValueFactory(new PropertyValueFactory<>("userID"));
            itemIDT.setCellValueFactory(new PropertyValueFactory<>("itemID"));
            quantityT.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            OrderBLL orderBLL = new OrderBLL();
            List<Orders> orderList = orderBLL.getAllOrders();

            if (orderList == null) {
                System.out.println("Error: Order list is null");
            }
            ordersTable.getItems().clear();

            for (Orders order : orderList) {
                int orderID = order.getOrderID();
                int userID = order.getUserID();
                int itemID = order.getItemID();
                int quantity = order.getQuantity();

                Orders newOrder = new Orders(orderID, userID, itemID, quantity);

                ObservableList<Orders> newOrders = ordersTable.getItems();
                newOrders.add(newOrder);
                ordersTable.setItems(newOrders);
            }
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }
    /**
     * Closes the application.
     *
     * @param event The MouseEvent that triggered the action.
     */
    @FXML
    public void handleClose(MouseEvent event) {
        System.exit(0);
    }
    /**
     * Handles the back button action by switching to the home view.
     *
     * @param event The MouseEvent that triggered the action.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    public void handleBack(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles the add order button action by inserting a new order into the database.
     *
     * @param event The MouseEvent that triggered the action.
     */
    @FXML
    public void handleAddOrder(MouseEvent event) {
        try {
            int orderID = Integer.parseInt(orderIDField.getText());
            int userID = userIDCombo.getValue(); // Get the selected user ID

            int itemID = itemIDCombo.getValue(); // Get the selected item ID

            int quantity = Integer.parseInt(quantityField.getText());

            Orders order = new Orders(orderID, userID, itemID, quantity);


            OrderBLL orderBLL = new OrderBLL();
            ItemBLL itembll = new ItemBLL();
            Items newItemTry = itembll.findItemsByID(itemID);
            int stock = newItemTry.getItemStock();
            if (stock < quantity) {
                givePopUp(event, "Not enough in stock");
            } else {

                orderBLL.insertNewOrder(order);
                newItemTry.setItemStock(stock - quantity);
                itembll.updateNewItem(newItemTry.getItemID(), newItemTry);

                Users user = userDAO.findByID(userID);
                Items item = itemDAO.findByID(itemID);
                double totalPrice = item.getItemPrice() * quantity;

                LocalDateTime time = LocalDateTime.now();
                String newTime = String.valueOf(time);
                Bill bill = new Bill(orderID, user.getUserName(), item.getItemName(), totalPrice, newTime);
                bill.insertIntoLogTable();
                outputOrders(bill);

            }
            // Show success message or update the table view
            showDataOrders(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }
    /**
     * Handles the update order button action by updating an existing order in the database.
     *
     * @param event The MouseEvent that triggered the action.
     */
    @FXML
    public void handleUpdateOrder(MouseEvent event) {
        try {
            int orderID = Integer.parseInt(orderIDField.getText());
            int userID = userIDCombo.getValue(); // Get the selected user ID

            int itemID = itemIDCombo.getValue(); // Get the selected item ID

            int quantity = Integer.parseInt(quantityField.getText());

            Orders order = new Orders(orderID, userID, itemID, quantity);

            ItemBLL itembll = new ItemBLL();
            Items newItemTry = itembll.findItemsByID(itemID);
            int stock = newItemTry.getItemStock();
            if (stock < quantity) {
                givePopUp(event, "Not enough in stock");
            } else {

                orderBLL.insertNewOrder(order);
                newItemTry.setItemStock(stock - quantity);
                itembll.updateNewItem(newItemTry.getItemID(), newItemTry);
                Users user = userDAO.findByID(userID);
                Items item = itemDAO.findByID(itemID);
                double totalPrice = item.getItemPrice() * quantity;

                LocalDateTime time = LocalDateTime.now();
                String newTime = String.valueOf(time);
                Bill bill = new Bill(orderID, user.getUserName(), item.getItemName(), totalPrice, newTime);
                bill.insertIntoLogTable();
                outputOrders(bill);

            }
            // Show success message or update the table view
            showDataOrders(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }
    /**
     * Outputs the order details.
     *
     * @param bill The bill object containing the order details.
     */
    public void outputOrders(Bill bill) {
        System.out.println("NEW ORDER CREATED");
        System.out.println("orderId: " + bill.orderId());
        System.out.println("client: " + bill.userName());
        System.out.println("product: " + bill.itemName());
        System.out.println("total price: " + bill.totalPrice());
        System.out.println(bill.date());
        System.out.println("THANK YOU SO MUCH, THIS MUST BE JUST WHAT I NEEDED");
    }
    /**
     * Handles the delete order button action by deleting an order from the database.
     *
     * @param event The MouseEvent that triggered the action.
     */
    @FXML
    public void handleDeleteOrder(MouseEvent event) {
        try {
            int orderID = Integer.parseInt(orderIDField.getText());

            OrderBLL orderBLL = new OrderBLL();
            orderBLL.deleteOrder(orderID);

            // Show success message or update the table view
            showDataOrders(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }
    /**
     * Displays an alert popup with the given message.
     *
     * @param event   The MouseEvent that triggered the action.
     * @param message The message to be displayed in the alert popup.
     */
    private void givePopUp(MouseEvent event, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(((Node) event.getSource()).getScene().getWindow());
        alert.showAndWait();
    }
}
