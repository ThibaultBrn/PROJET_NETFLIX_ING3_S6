package recherchefilm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class AjoutFilm implements Initializable {

    /**
     * Récupération des zones de textes pour la saisie d'informations
     */
    @FXML private TextField nomFilm;
    @FXML private TextField dureeFilm;
    @FXML private TextField lienFilm;
    @FXML private TextField synopsisFilm;
    @FXML private TextField nomReal;
    @FXML private TextField prenomReal;
    @FXML private TextField anneeSortie;
    @FXML private TextField miniature;
    /**
     * Menu déroulant de choix de catégories
     */
    @FXML private ChoiceBox selectCate;
    @FXML HBox ajoutCate;

    /**
     *Champ de texte pour l'ajout d'une nouvelle catégorie
     */
    @FXML TextField nouvelleCate;


    /**
     * Méthode permettant d'ajouter le film dans la base de donnée
     */
    @FXML
    protected void ajouterFilm()
    {

        /** Affichage des informations du film*/
        System.out.println("Duree:" + Integer.parseInt(dureeFilm.getText()));
        System.out.println("Lien:" + lienFilm.getText());
        System.out.println("Synopsis:" + synopsisFilm.getText());
        System.out.println("Nom Real:" + nomReal.getText());
        System.out.println("Prenom Real:" + prenomReal.getText());
        System.out.println("Annee:" + Integer.parseInt(anneeSortie.getText()));
        System.out.println("Miniature:" + miniature.getText());
        System.out.println("Categorie :" + selectCate.getValue().toString());

        /** Insertion du film dans la table films avec les valeurs des champs de texte*/
        SQLPart.ajoutDonnee(nomFilm.getText().replace("'","''"),Integer.parseInt(dureeFilm.getText()),lienFilm.getText(),synopsisFilm.getText().replace("'","''"),nomReal.getText().replace("'","''"),prenomReal.getText().replace("'","''"), Integer.parseInt(anneeSortie.getText()), miniature.getText());

        /**Cas ou l'on ajoute une nouvelle catégorie*/
        if (Objects.equals(selectCate.getValue().toString(), "---Ajouter Catégorie---"))
        {
            /**Ajout de la nouvelle catégorie dans la table categorisation*/
            SQLPart.addCategories(nouvelleCate.getText(),"");
            /**Insertion du film dans la table catégories*/
            SQLPart.addCategories(nouvelleCate.getText(),nomFilm.getText().replace("'","''"));
        }
        else
        {
            /**Insertion du film dans la table catégories*/
            SQLPart.addCategories(selectCate.getValue().toString(),nomFilm.getText().replace("'","''"));
        }

        /**Remise à zéro des valeurs de la catégorie*/
        selectCate.setValue("");
        /**Mise à jour des valeurs possibles pour les catégories*/
        recupCate();

    }

    /**
     *Méthode permettant de mettre à jour les valeurs possibles pour les catégories
     */
    void recupCate()
    {
        /**Mise à zéro des valeurs*/
        selectCate.getItems().removeAll();
        selectCate.getItems().clear();

        /**Récupération des différentes catégories présentes dans la table categorisation*/
        ResultSet rs = SQLPart.getCategories();

        /**Parcours des catégories récupérées */
        if (rs != null) {
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    /**Ajout de la catégorie dans le menu de choix */
                    selectCate.getItems().addAll(rs.getString("Categorie"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            /**Masquage de la zone de texte pour ajouter une catégorie*/
            nouvelleCate.setVisible(false);

        }
    }

    /**
     * Méthode au lancement de la page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        /**Récupération des catégories*/
        recupCate();


        /** Listener sur la modification de la valeur du menu*/
        selectCate.setOnAction(e-> {

            /** Choix non vide*/
            if (selectCate.getValue() !=null)
            {
                /** Choix ajout d'une nouvelle catégorie*/
                if (Objects.equals(selectCate.getValue().toString(), "---Ajouter Catégorie---"))
                {
                    System.out.println("Changement");

                    /** Affichage de la zone de texte*/
                    nouvelleCate.setVisible(true);


                }
                else
                {
                    /** Maquage de la zone de texte*/
                    nouvelleCate.setVisible(false);
                }
            }

        });

    }
}
