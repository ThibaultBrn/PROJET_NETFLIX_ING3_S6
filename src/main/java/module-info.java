module RechercheFilm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires java.sql;
    requires java.desktop;
    opens recherchefilm to javafx.fxml;
    exports recherchefilm;
}
