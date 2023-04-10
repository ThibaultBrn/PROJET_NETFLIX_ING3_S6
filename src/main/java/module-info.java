module com.example.recherchefilm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    opens com.example.recherchefilm to javafx.fxml;
    exports com.example.recherchefilm;
}