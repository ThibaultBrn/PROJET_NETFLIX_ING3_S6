package recherchefilm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class gestionCategorie implements Initializable {
    @FXML private ToggleGroup groupe;
    @FXML private RadioButton choixAjout;
    @FXML private RadioButton choixSupprimer;

    @FXML private ScrollPane scrollPaneSupprimer;

    @FXML private Button boutonSupprimer;
    @FXML private VBox vBoxSupprimer;
    @FXML private Label labelPseudo;
    @FXML private TextField pseudoUser;

    @FXML private Button boutonAjouter;

    private String pseudoSupprime;


    void verif()
    {

        for(Node n: vBoxSupprimer.getChildren())
        {
            HBox boxsuppr = (HBox)n;
            Label labelSuppr = (Label)boxsuppr.getChildren().get(0);

            Stream.of(labelSuppr).map(Label::getText).forEach(text -> {
                if(Objects.equals(text, pseudoSupprime))
                {
                    n.setStyle("-fx-border-color:purple; -fx-background-color:rgba(150, 150, 150, 0.2);-fx-border-width: 2");
                }
                else
                {
                    n.setStyle("-fx-border-color:black; -fx-background-color:black;-fx-border-width: 2");
                }
            });
        }
    }
    private void affichageComptes()
    {
        vBoxSupprimer.getChildren().clear();
        example.BaseDeDonnees bdd = new example.BaseDeDonnees("projet_netflix","root","");
        bdd.requeteSQL("Select DISTINCT Categorie FROM categorisation WHERE (Categorie!='---Ajouter Catégorie---' AND Categorie!='')");
        ResultSet rs = bdd.getResultat();

        try {

            Label Nom = new Label("Catégorie");

            Nom.setMaxWidth(150);
            Nom.setMinWidth(150);
            Nom.setWrapText(true);
            Nom.setFont(new Font(23));
            Nom.setStyle("-fx-text-fill: purple");
            Nom.setUnderline(true);
            Nom.setPadding(new Insets(10,0,30,20));

            HBox boxUtilisateur = new HBox();
            boxUtilisateur.getChildren().addAll(Nom);
            vBoxSupprimer.getChildren().addAll(boxUtilisateur);

            while (rs.next())
            {
                HBox boxUtilisateur2 = new HBox();
                Label Nom2 = new Label(rs.getString("Categorie"));

                Nom2.setMaxWidth(150);
                Nom2.setMinWidth(150);
                Nom2.setWrapText(true);
                Nom2.setFont(new Font(20));
                Nom2.setStyle("-fx-text-fill: white");
                Nom2.setPadding(new Insets(0,0,0,20));


                boxUtilisateur2.getChildren().addAll(Nom2);

                boxUtilisateur2.setOnMouseEntered(e ->
                {
                    boxUtilisateur2.setStyle("-fx-border-color:purple; -fx-background-color:rgba(150, 150, 150, 0.2);-fx-border-width: 2");
                    boxUtilisateur2.setCursor(Cursor.HAND);
                });

                boxUtilisateur2.setOnMouseExited(e ->
                {
                    verif();
                    //boxUtilisateur2.setStyle("-fx-border-color:black; -fx-background-color:black;-fx-border-width: 2");
                });

                boxUtilisateur2.setOnMouseClicked(e ->
                {
                    Stream.of(Nom2).map(Label::getText).forEach(text -> {
                        pseudoSupprime = text;
                        System.out.println(pseudoSupprime);
                        // whatever you need to do with the text...
                    });
                    verif();
                });

                vBoxSupprimer.getChildren().addAll(boxUtilisateur2);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        scrollPaneSupprimer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneSupprimer.setVisible(false);
        boutonSupprimer.setVisible(false);

        labelPseudo.setVisible(false);
        boutonAjouter.setVisible(false);
        pseudoUser.setVisible(false);



        boutonAjouter.setOnMouseEntered(e->
        {
            boutonAjouter.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
            boutonAjouter.setCursor(Cursor.HAND);
        });
        boutonAjouter.setOnMouseExited(e-> boutonAjouter.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));

        boutonAjouter.setOnMouseClicked(e->
        {
            example.BaseDeDonnees bdd = new example.BaseDeDonnees("projet_netflix","root","");
            bdd.requeteSQL("INSERT INTO categorisation VALUES('','"+ pseudoUser.getText()+"')");
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow(); // get reference to current stage
            currentStage.close();
        });


        boutonSupprimer.setOnMouseEntered(e->
        {
            boutonSupprimer.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
            boutonSupprimer.setCursor(Cursor.HAND);
        });
        boutonSupprimer.setOnMouseExited(e-> boutonSupprimer.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));

        boutonSupprimer.setOnMouseClicked(e->
        {
            example.BaseDeDonnees bdd = new example.BaseDeDonnees("projet_netflix","root","");
            bdd.requeteSQL("DELETE FROM films WHERE  films.nomFilm IN (SELECT NomFilm FROM categorisation WHERE Categorie ='"+pseudoSupprime+"')");
            bdd.requeteSQL("DELETE FROM listelecture WHERE  listelecture.nomFilm IN (SELECT NomFilm FROM categorisation WHERE Categorie ='"+pseudoSupprime+"')");
            bdd.requeteSQL("DELETE FROM categorisation WHERE Categorie ='"+pseudoSupprime+"'");
            affichageComptes();
        });

        groupe.selectedToggleProperty().addListener(e->
        {
            if (groupe.getSelectedToggle() == choixAjout)
            {
                System.out.println("Ajout");
                scrollPaneSupprimer.setVisible(false);
                boutonSupprimer.setVisible(false);

                labelPseudo.setVisible(true);
                boutonAjouter.setVisible(true);
                pseudoUser.setVisible(true);
            }

            else if (groupe.getSelectedToggle() == choixSupprimer)
            {

                System.out.println("Suppression");
                scrollPaneSupprimer.setVisible(true);
                boutonSupprimer.setVisible(true);

                labelPseudo.setVisible(false);
                boutonAjouter.setVisible(false);
                pseudoUser.setVisible(false);


                affichageComptes();
            }

        });

    }
}