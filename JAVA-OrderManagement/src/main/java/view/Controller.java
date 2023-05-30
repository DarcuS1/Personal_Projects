package view;

import com.example.demo1.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //region declarations for users
    private Label label;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleUsers(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleOrders(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("order.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBItems(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("item.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void givePopUp2(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("incorrectPersonPop.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    public void givePopUp3(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("userCreationError.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }


    //endregion
}
