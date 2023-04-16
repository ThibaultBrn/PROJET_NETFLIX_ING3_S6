package recherchefilm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class changerPage {

    public static void ouvrirRecherche(MouseEvent e, String Pseudo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rechercheFilm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 676, 400);
        Stage stage = new Stage();
        rechercheFilm controller = fxmlLoader.getController();
        controller.setPseudo(Pseudo);
        stage.setTitle("Rechercher un film");
        stage.setScene(scene);
        stage.setMaximized(true);

        Stage CurrentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.show();
        CurrentStage.close();
    }

    public static void ouvrirFilm(String nomFilm, String Pseudo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(pageAccueil.class.getResource("pageFilm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Stage stage = new Stage();
        pageFilm controller = fxmlLoader.getController();
        controller.setNomFilm(nomFilm);
        System.out.println("VOICI LE PSEUDO AVANT PAGE FILM : " + Pseudo);
        controller.setPseudo(Pseudo);

        stage.setTitle(nomFilm);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public static void retourLogin(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 676, 400);
        Stage stage = new Stage();
        stage.setTitle("Bienvenue sur Netflix!");
        stage.setScene(scene);
        stage.setResizable(false);

        Stage CurrentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        CurrentStage.close();


        stage.show();
    }
    public static void retourMenu(ActionEvent event, String Pseudo) throws IOException {

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        FXMLLoader fxmlLoader = new FXMLLoader(pageFilmApp.class.getResource("pageAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Stage stage = new Stage();
        pageAccueil controller = fxmlLoader.getController();
        controller.setPseudo(Pseudo);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        currentStage.close();
    }

    public static void retourMenu(MouseEvent event, String Pseudo) throws IOException {

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        FXMLLoader fxmlLoader = new FXMLLoader(pageFilmApp.class.getResource("pageAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Stage stage = new Stage();
        pageAccueil controller = fxmlLoader.getController();
        controller.setPseudo(Pseudo);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        currentStage.close();
    }
}
