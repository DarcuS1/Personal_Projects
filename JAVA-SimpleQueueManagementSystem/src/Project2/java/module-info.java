module main.pt2023_30421_gelusamuel_josan_assignment_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens main to javafx.fxml;
    exports main;
    exports main.controller;
    opens main.controller to javafx.fxml;
}