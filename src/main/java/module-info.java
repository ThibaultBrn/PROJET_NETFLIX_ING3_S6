module pageFilm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires java.sql;
    requires java.desktop;
    opens pagefilm to javafx.fxml;
    exports pagefilm;

}
