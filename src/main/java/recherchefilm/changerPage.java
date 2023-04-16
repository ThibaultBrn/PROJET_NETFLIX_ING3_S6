package recherchefilm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class changerPage {

    /**
     * Chargement et ouverture de la page de recherche de films
     */
    public static void ouvrirRecherche(MouseEvent e, String Pseudo) throws IOException {

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rechercheFilm.fxml"));

        /** Creation de la nouvelle page*/
        Scene scene = new Scene(fxmlLoader.load(), 676, 400);
        Stage stage = new Stage();

        /**Passage du pseudo dans la nouvelle fenetre */
        rechercheFilm controller = fxmlLoader.getController();
        controller.setPseudo(Pseudo);

        /** Creation de la nouvelle page*/
        stage.setTitle("Rechercher un film");
        stage.setScene(scene);
        stage.setMaximized(true);

        /** Recuperation de la page actuelle*/
        Stage CurrentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.show();

        /** Fermeture de la page actuelle*/
        CurrentStage.close();
    }


    /**
     * Chargement et ouverture de la page d'information d'un film'
     */
    public static void ouvrirFilm(String nomFilm, String Pseudo) throws IOException {

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pageFilm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();

        /**Passage du nom du film dans la nouvelle fenetre */
        pageFilm controller = fxmlLoader.getController();
        controller.setNomFilm(nomFilm);
        System.out.println("VOICI LE PSEUDO AVANT PAGE FILM : " + Pseudo);
        controller.setPseudo(Pseudo);

        /** Creation de la nouvelle page*/
        stage.setTitle(nomFilm);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    /**
     * Chargement et ouverture de la page de login
     */
    public static void retourLogin(MouseEvent e) throws IOException {
        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 676, 400);

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Bienvenue sur Netflix!");
        stage.setScene(scene);
        stage.setResizable(false);

        /** Recuperation de la page actuelle*/
        Stage CurrentStage = (Stage)((Node) e.getSource()).getScene().getWindow();

        /** Fermeture de la page actuelle*/
        CurrentStage.close();


        stage.show();
    }

    /**
     * Chargement et ouverture de la page d'accueil avec ActionListener
     */
    public static void retourMenu(ActionEvent event, String Pseudo) throws IOException {

        /** Recuperation de la page actuelle*/
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pageAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();

        /**Passage du pseudo dans la nouvelle fenetre */
        pageAccueil controller = fxmlLoader.getController();
        controller.setPseudo(Pseudo);

        /** Creation de la nouvelle page*/
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        /** Fermeture de la page actuelle*/
        currentStage.close();
    }


    /**
     * Chargement et ouverture de la page d'accueil avec Mouselistener
     */
    public static void retourMenu(MouseEvent event, String Pseudo) throws IOException {

        /** Recuperation de la page actuelle*/
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pageAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();

        /**Passage du pseudo dans la nouvelle fenetre */
        pageAccueil controller = fxmlLoader.getController();
        controller.setPseudo(Pseudo);

        /** Creation de la nouvelle page*/
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        /** Fermeture de la page actuelle*/
        currentStage.close();
    }

    /**
     * Chargement et ouverture de la page d'ajout de film
     */
    public static void ajoutFilm(MouseEvent event){

        /** Recuperation de la page actuelle*/
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ajoutFilm.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Chargement et ouverture de la page de gestion administrateur
     */
    public static void pageAdmin(ActionEvent event){

        /** Recuperation de la page actuelle*/
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pageAdmin.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        /** Fermeture de la page actuelle*/
        currentStage.close();
    }

    /**
     * Chargement et ouverture de la page de gestion d'utilisateurs
     */
    public static void gestionUtilisateurs(MouseEvent event){

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionUtilisateur.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Chargement et ouverture de la page de gestion des catégories
     */
    public static void gestionCategories(MouseEvent event){

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(gestionCategorie.class.getResource("gestionCategorie.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Chargement et ouverture de la page de modification de films
     */
    public static void modifFilm(MouseEvent event){

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifFilm.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Chargement et ouverture de la page de gestion des catégories
     */
    public static void gestionCategorie(MouseEvent event){

        /** Chargement de la page FXML */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionCategorie.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Creation de la nouvelle page*/
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
