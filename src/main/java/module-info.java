module RechercheFilm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    opens com.example.RechercheFilm to javafx.fxml;
    exports com.example.RechercheFilm;

}