module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires javafx.swing;


    opens shopping to javafx.fxml;
    exports shopping;
    exports shopping.other;
    opens shopping.other to javafx.fxml;
}