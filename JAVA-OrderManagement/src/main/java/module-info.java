module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.demo1 to javafx.fxml;
    opens model to javafx.base;
    opens view to javafx.fxml;
    exports com.example.demo1;
    exports view;
}