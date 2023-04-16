package recherchefilm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    /**
     * Chargement de la première page du programme
     */
    @Override
    public void start(Stage stage) throws IOException {
        /**Chargement de la page FXML*/
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        /**Création d'une nouvelle scène*/
        Scene scene = new Scene(fxmlLoader.load(), 676, 400);

        /**Infos de la page*/
        stage.setTitle("Bienvenue sur Netflix!");
        stage.setScene(scene);
        stage.setResizable(false);

        /**Affichage de la page*/
        stage.show();
    }

    /**
     * Lancement de la première page du programme
     */
    public static void main(String[] args)
    {

        launch();
    }
}