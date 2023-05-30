/**
 * @author Josan Gelu-Samuel
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class JFXML_LoginForm1 extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("V/FXMLDocument.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
       launch(args);
    }

}
