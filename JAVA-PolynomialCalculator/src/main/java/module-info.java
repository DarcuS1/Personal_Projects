module org.openjfx.javafxmavenarchetypes {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens org.openjfx.pt_proj to javafx.fxml;
    exports org.openjfx.pt_proj;
    exports org.openjfx.pt_proj.controller;
    opens org.openjfx.pt_proj.controller to javafx.fxml;
}