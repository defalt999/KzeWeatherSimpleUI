module defalt.kze.kzeweathermax {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.desktop;


    opens defalt.kze.kzeweathermax to javafx.fxml;
    exports defalt.kze.kzeweathermax;
}