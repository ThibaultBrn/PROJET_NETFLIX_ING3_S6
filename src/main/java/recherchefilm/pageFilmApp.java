package recherchefilm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class pageFilmApp extends Application {


    public static String nomFilm;



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(pageFilmApp.class.getResource("pageFilm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        pageFilm controller = fxmlLoader.getController();
        controller.setNomFilm(nomFilm);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String[] args)
    {
        nomFilm = "Avatar";
        launch();
    }
}