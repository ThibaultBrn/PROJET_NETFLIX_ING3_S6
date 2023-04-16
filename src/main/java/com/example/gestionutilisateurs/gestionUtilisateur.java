package com.example.gestionutilisateurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class gestionUtilisateur implements Initializable {
    @FXML private ToggleGroup groupe;
    @FXML private RadioButton choixAjout;
    @FXML private RadioButton choixSupprimer;

    @FXML private ScrollPane scrollPaneSupprimer;

    @FXML private Button boutonSupprimer;
    @FXML private VBox vBoxSupprimer;
    @FXML private Label labelPseudo;
    @FXML private Label labelMdp;
    @FXML private Label labelDroits;
    @FXML private TextField pseudoUser;
    @FXML private TextField mdpUser;
    @FXML private Button boutonAjouter;
    @FXML private RadioButton choixUser;
    @FXML private RadioButton choixAdmin;
    @FXML private ToggleGroup groupeDroits;
    @FXML private Pane paneUser;
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
        BaseDeDonnees bdd = new BaseDeDonnees("projet_netflix","root","");
        bdd.requeteSQL("Select * FROM utilisateur");
        ResultSet rs = bdd.getResultat();

        try {

            Label Nom = new Label("Pseudo");
            Label MotDePasse =  new Label("MotDePasse");

            Nom.setMaxWidth(150);
            Nom.setMinWidth(150);
            Nom.setWrapText(true);
            Nom.setFont(new Font(23));
            Nom.setStyle("-fx-text-fill: purple");
            Nom.setUnderline(true);
            Nom.setPadding(new Insets(10,0,30,20));

            MotDePasse.setMaxWidth(150);
            MotDePasse.setMinWidth(150);
            MotDePasse.setWrapText(true);
            MotDePasse.setFont(new Font(23));
            MotDePasse.setStyle("-fx-text-fill: purple");
            MotDePasse.setUnderline(true);
            MotDePasse.setPadding(new Insets(10,0,30,20));


            HBox boxUtilisateur = new HBox();
            boxUtilisateur.getChildren().addAll(Nom);
            boxUtilisateur.getChildren().addAll(MotDePasse);
            vBoxSupprimer.getChildren().addAll(boxUtilisateur);

            while (rs.next())
            {
                HBox boxUtilisateur2 = new HBox();
                Label Nom2 = new Label(rs.getString("Pseudo"));
                MotDePasse =  new Label(rs.getString("MotDePasse"));

                Nom2.setMaxWidth(150);
                Nom2.setMinWidth(150);
                Nom2.setWrapText(true);
                Nom2.setFont(new Font(20));
                Nom2.setStyle("-fx-text-fill: white");
                Nom2.setPadding(new Insets(0,0,0,20));

                MotDePasse.setMaxWidth(150);
                MotDePasse.setMinWidth(150);
                MotDePasse.setWrapText(true);
                MotDePasse.setFont(new Font(20));
                MotDePasse.setStyle("-fx-text-fill: white");
                MotDePasse.setPadding(new Insets(0,0,0,20));


                boxUtilisateur2.getChildren().addAll(Nom2);
                boxUtilisateur2.getChildren().addAll(MotDePasse);

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
        labelMdp.setVisible(false);
        labelDroits.setVisible(false);
        pseudoUser.setVisible(false);
        mdpUser.setVisible(false);
        boutonAjouter.setVisible(false);
        paneUser.setVisible(false);
        choixUser.setVisible(false);
        choixAdmin.setVisible(false);


        boutonAjouter.setOnMouseEntered(e->
        {
            boutonAjouter.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
            boutonAjouter.setCursor(Cursor.HAND);
        });
        boutonAjouter.setOnMouseExited(e-> boutonAjouter.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));

        boutonAjouter.setOnMouseClicked(e->
        {
            if(groupeDroits.getSelectedToggle() == choixUser)
            {
                BaseDeDonnees bdd = new BaseDeDonnees("projet_netflix","root","");
                bdd.requeteSQL("INSERT INTO utilisateur VALUES('"+pseudoUser.getText()+"','"+mdpUser.getText()+"',0)");
            }
            else if(groupeDroits.getSelectedToggle() == choixAdmin)
            {
                BaseDeDonnees bdd = new BaseDeDonnees("projet_netflix","root","");
                String requete = "INSERT INTO utilisateur VALUES('"+pseudoUser.getText()+"','"+mdpUser.getText()+"',1)";
                System.out.println("LA REQUETE: "+requete);
                bdd.requeteSQL(requete);
            }

        });


        boutonSupprimer.setOnMouseEntered(e->
        {
            boutonSupprimer.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
            boutonSupprimer.setCursor(Cursor.HAND);
        });
        boutonSupprimer.setOnMouseExited(e-> boutonSupprimer.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));

        boutonSupprimer.setOnMouseClicked(e->
        {
            BaseDeDonnees bdd = new BaseDeDonnees("projet_netflix","root","");
            bdd.requeteSQL("DELETE FROM utilisateur WHERE Pseudo ='"+pseudoSupprime+"'");
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
                labelMdp.setVisible(true);
                labelDroits.setVisible(true);
                pseudoUser.setVisible(true);
                mdpUser.setVisible(true);
                boutonAjouter.setVisible(true);
                paneUser.setVisible(true);
                choixUser.setVisible(true);
                choixAdmin.setVisible(true);
            }
            else if (groupe.getSelectedToggle() == choixSupprimer)
            {

                System.out.println("Suppression");
                scrollPaneSupprimer.setVisible(true);
                boutonSupprimer.setVisible(true);

                labelPseudo.setVisible(false);
                labelMdp.setVisible(false);
                labelDroits.setVisible(false);
                pseudoUser.setVisible(false);
                mdpUser.setVisible(false);
                boutonAjouter.setVisible(false);
                paneUser.setVisible(false);
                choixUser.setVisible(false);
                choixAdmin.setVisible(false);

                affichageComptes();
            }

        });

    }
}