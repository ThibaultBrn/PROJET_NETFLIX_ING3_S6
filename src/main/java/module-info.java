module com.example.recherchefilm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.web;
    opens recherchefilm to javafx.fxml;
    exports recherchefilm;
}