package view;

import buss.UserBLL;
import com.example.demo1.HelloApplication;
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
import model.Users;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerUsers implements Initializable {

    //region declarations for users
    private Label label;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField userIDField;
    @FXML
    TextField userNameField;
    @FXML
    TextField userEmailField;
    @FXML
    TextField userPhoneField;
    @FXML
    TextField userAddressField;

    @FXML
    Button showDataUserBtn;

    @FXML
    Button addUserBtn;

    @FXML
    Button updateUserBtn;

    @FXML
    Button deleteUserBtn;

    @FXML
    private TableView<Users> userTable;

    @FXML
    private TableColumn<Users, Integer> userID;
    @FXML
    private TableColumn<Users, String> userName;
    @FXML
    private TableColumn<Users, String> userEmail;
    @FXML
    private TableColumn<Users, String> userPhone;
    @FXML
    private TableColumn<Users, String> userAddress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void showDataUsers(MouseEvent event) {
        try {
            userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            userEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
            userPhone.setCellValueFactory(new PropertyValueFactory<>("userPhone"));
            userAddress.setCellValueFactory(new PropertyValueFactory<>("userAddress"));

            UserBLL userBLL = new UserBLL();
            List<Users> userList = userBLL.getAllUsers();

            if (userList == null) {
                System.out.println("Error: User list is null");
            }
            userTable.getItems().clear();

            for (Users user : userList) {
                int userID = user.getUserID();
                String userName = user.getUserName();
                String userEmail = user.getUserEmail();
                int userPhone = user.getUserPhone();
                String userAddress = user.getUserAddress();

                Users newUser = new Users(userID, userName, userEmail, userPhone, userAddress);

                ObservableList<Users> newUsers = userTable.getItems();
                newUsers.add(newUser);
                userTable.setItems(newUsers);
            }
        } catch (Exception e) {
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
    public void handleAddUser(MouseEvent event) throws IOException {
        try {
            int userID = Integer.parseInt(userIDField.getText());
            String userName = userNameField.getText();
            String userEmail = userEmailField.getText();
            int userPhone = Integer.parseInt(userPhoneField.getText());
            String userAddress = userAddressField.getText();

            Users user = new Users(userID, userName, userEmail, userPhone, userAddress);

            UserBLL userBLL = new UserBLL();
            userBLL.insertNewUser(user);

            // Show success message or update the table view
            showDataUsers(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }

    @FXML
    public void handleUpdateUser(MouseEvent event) throws IOException {
        try {
            int userID = Integer.parseInt(userIDField.getText());
            String userName = userNameField.getText();
            String userEmail = userEmailField.getText();
            int userPhone = Integer.parseInt(userPhoneField.getText());
            String userAddress = userAddressField.getText();

            Users user = new Users(userID, userName, userEmail, userPhone, userAddress);

            UserBLL userBLL = new UserBLL();
            userBLL.updateUser(userID, user);

            // Show success message or update the table view
            showDataUsers(null);
        } catch (Exception e) {
            givePopUp(event, e.getMessage());
        }
    }

    @FXML
    public void handleDeleteUser(MouseEvent event) throws IOException {
        try {
            int userID = Integer.parseInt(userIDField.getText());

            UserBLL userBLL = new UserBLL();
            userBLL.deleteUser(userID);

            // Show success message or update the table view
            showDataUsers(null);
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
}
