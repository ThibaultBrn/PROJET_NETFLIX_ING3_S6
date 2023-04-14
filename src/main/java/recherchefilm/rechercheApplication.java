package recherchefilm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class rechercheApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SQLPart.connectionSQL();
        FXMLLoader fxmlLoader = new FXMLLoader(rechercheApplication.class.getResource("rechercheFilm.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Recherche Film");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args)  {
        launch();
    }
}