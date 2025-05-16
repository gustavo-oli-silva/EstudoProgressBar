module com.example.estudoprogressbar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.estudoprogressbar to javafx.fxml;
    exports com.example.estudoprogressbar;
    exports com.example.estudoprogressbar.controllers;
    opens com.example.estudoprogressbar.controllers to javafx.fxml;
}