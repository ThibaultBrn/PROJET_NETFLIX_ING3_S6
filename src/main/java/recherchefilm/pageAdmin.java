package recherchefilm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class pageAdmin implements Initializable {

    @FXML Button ajouterFilm;
    @FXML Button modifierFilm;
    @FXML Button gestionCategories;
    @FXML Button gestionUtilisateur;
    @FXML Button retour;


    void initBouton(Button bout )
    {
        bout.setOnMouseEntered(e ->
        {
            bout.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
            bout.setCursor(Cursor.HAND);
        });
        bout.setOnMouseExited(e -> bout.setStyle("-fx-border-color:purple;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initBouton(ajouterFilm);
        initBouton(modifierFilm);
        initBouton(gestionCategories);
        initBouton(gestionUtilisateur);
        initBouton(retour);

        ajouterFilm.setOnMouseClicked(changerPage::ajoutFilm);

        modifierFilm.setOnMouseClicked(changerPage::modifFilm);

        gestionCategories.setOnMouseClicked(changerPage::gestionCategories);

        gestionUtilisateur.setOnMouseClicked(changerPage::gestionUtilisateurs);


        retour.setOnMouseClicked(e-> {
            try {
                changerPage.retourLogin(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });




    }
}
