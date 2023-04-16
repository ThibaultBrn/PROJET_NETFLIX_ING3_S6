module com.example.gestionutilisateurs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.gestionutilisateurs to javafx.fxml;
    exports com.example.gestionutilisateurs;
}