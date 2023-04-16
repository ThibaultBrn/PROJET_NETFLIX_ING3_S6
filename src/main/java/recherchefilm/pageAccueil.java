package recherchefilm;

import example.BaseDeDonnees;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class pageAccueil implements Initializable {


    private String Pseudo;

    public void setPseudo(String pseudo){
        Pseudo = pseudo;
        System.out.println("VOICI LE PSEUDO LORS DE L'AJOUT : " + Pseudo);
    }

    @FXML private VBox virtBox;

    @FXML private Label labelNom;
    @FXML private Button boutonDeco;
    @FXML TextField barreRecherche;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Platform.runLater(() ->
        {
            System.out.println("VOICI LE PSEUDO AVANT REQUETE : " + Pseudo);
            BaseDeDonnees BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
            BDD_Projet_Netflix.requeteSQL("select NomFilm from listelecture WHERE Pseudo ='"+Pseudo+"'");
            System.out.println("VOICI LE PSEUDO : " + Pseudo);

            labelNom.setText("Bonjour "+ Pseudo);

            boutonDeco.setOnMouseClicked(e->
            {
                try {
                    changerPage.retourLogin(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            boutonDeco.setOnMouseEntered(e->
            {
                boutonDeco.setStyle("-fx-text-fill: purple; -fx-background-color: black");
                boutonDeco.setCursor(Cursor.HAND);
            });
            boutonDeco.setOnMouseExited(e->
            {
                boutonDeco.setStyle("-fx-text-fill: white; -fx-background-color: black");
                boutonDeco.setCursor(Cursor.HAND);
            });

            barreRecherche.setOnMouseClicked(e->
            {
                try {
                    changerPage.ouvrirRecherche(e,Pseudo);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            ResultSet resultatListe = BDD_Projet_Netflix.getResultat();
            try
            {
                if (resultatListe != null)
                {
                    if(resultatListe.next())
                    {
                        Label nomCat = new Label();
                        nomCat.setText("Ma liste de lecture");
                        nomCat.setFont(new Font(35));
                        nomCat.setTextFill(Color.PURPLE);
                        nomCat.setUnderline(true);
                        nomCat.setPadding(new Insets(40,0,10,0));
                        virtBox.getChildren().addAll(nomCat);
                        ScrollPane paneCate = new ScrollPane();
                        HBox boxCate =new HBox();
                        paneCate.setContent(boxCate);
                        paneCate.setMinHeight(310);
                        paneCate.setStyle("-fx-background-color:black");
                        paneCate.setFitToWidth(true);

                        boxCate.setFillHeight(true);
                        boxCate.setStyle("-fx-background-color:black");
                        boxCate.setSpacing(15);
                        virtBox.getChildren().addAll(paneCate);






                        String film1 = resultatListe.getString("NomFilm");
                        //System.out.println("Le fim :" + film1);


                        BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
                        BDD_Projet_Netflix.requeteSQL("select * from films WHERE NomFilm = '" +film1+ "'");
                        ResultSet resultatTemp = BDD_Projet_Netflix.getResultat();




                        if (resultatTemp != null)
                        {

                            while (resultatTemp.next())
                            {
                                String NomFilm = resultatTemp.getString("NomFilm");
                                String miniaFilm = resultatTemp.getString("Miniature");

                                //System.out.println("Le fim la:" + NomFilm);
                                //System.out.println("Le lien la:" + miniaFilm);

                                StackPane paneMinia = new StackPane();
                                paneMinia.setAlignment(Pos.CENTER);

                                Label textFilm = new Label(NomFilm);
                                textFilm.setFont(new Font(35));
                                textFilm.setStyle("-fx-text-fill: white");
                                textFilm.setMaxWidth(200);
                                textFilm.setWrapText(true);
                                textFilm.setTextAlignment(TextAlignment.CENTER);
                                textFilm.setAlignment(Pos.CENTER);


                                ImageView minia = new ImageView();
                                Image image = new Image(miniaFilm);


                                minia.setImage(image);
                                minia.setFitHeight(300);
                                minia.setFitWidth(200);
                                //minia.setStyle("-fx-opacity:50");

                                paneMinia.setOnMouseEntered(e->{
                                    minia.setOpacity(0.3);
                                    minia.setCursor(Cursor.HAND);
                                    paneMinia.setStyle("-fx-border-color:white;-fx-border-width:4");
                                    paneMinia.getChildren().addAll(textFilm);

                                });
                                paneMinia.setOnMouseExited(e-> {
                                    minia.setOpacity(1);
                                    paneMinia.setStyle("-fx-border-color:purple;-fx-border-width:4");
                                    paneMinia.getChildren().removeAll(textFilm);
                                });

                                paneMinia.setOnMouseClicked(e ->
                                {
                                    try {
                                        changerPage.ouvrirFilm(NomFilm,Pseudo);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }

                                    Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                                    stage.close();
                                });

                                paneMinia.getChildren().addAll(minia);
                                paneMinia.setStyle("-fx-border-color:purple;-fx-border-width:4");
                                boxCate.getChildren().addAll(paneMinia);

                            }
                        }











                        while (resultatListe.next())
                        {
                            String film = resultatListe.getString("NomFilm");
                            //System.out.println("Le fim :" + film);


                            BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
                            BDD_Projet_Netflix.requeteSQL("select * from films WHERE NomFilm = '" +film+ "'");
                            ResultSet resultat3 = BDD_Projet_Netflix.getResultat();




                            if (resultat3 != null)
                            {

                                while (resultat3.next())
                                {
                                    String NomFilm = resultat3.getString("NomFilm");
                                    String miniaFilm = resultat3.getString("Miniature");

                                    //System.out.println("Le fim la:" + NomFilm);
                                    //System.out.println("Le lien la:" + miniaFilm);

                                    StackPane paneMinia = new StackPane();
                                    paneMinia.setAlignment(Pos.CENTER);

                                    Label textFilm = new Label(NomFilm);
                                    textFilm.setFont(new Font(35));
                                    textFilm.setStyle("-fx-text-fill: white");
                                    textFilm.setMaxWidth(200);
                                    textFilm.setWrapText(true);
                                    textFilm.setTextAlignment(TextAlignment.CENTER);
                                    textFilm.setAlignment(Pos.CENTER);


                                    ImageView minia = new ImageView();
                                    Image image = new Image(miniaFilm);

                                    minia.setImage(image);
                                    minia.setFitHeight(300);
                                    minia.setFitWidth(200);
                                    //minia.setStyle("-fx-opacity:50");

                                    paneMinia.setOnMouseEntered(e->{
                                        minia.setOpacity(0.3);
                                        minia.setCursor(Cursor.HAND);
                                        paneMinia.setStyle("-fx-border-color:white;-fx-border-width:4");
                                        paneMinia.getChildren().addAll(textFilm);

                                    });
                                    paneMinia.setOnMouseExited(e-> {
                                        minia.setOpacity(1);
                                        paneMinia.setStyle("-fx-border-color:purple;-fx-border-width:4");
                                        paneMinia.getChildren().removeAll(textFilm);
                                    });

                                    paneMinia.setOnMouseClicked(e ->
                                    {
                                        try {
                                            changerPage.ouvrirFilm(NomFilm,Pseudo);
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                                        stage.close();
                                    });

                                    paneMinia.getChildren().addAll(minia);
                                    paneMinia.setStyle("-fx-border-color:purple;-fx-border-width:4");
                                    boxCate.getChildren().addAll(paneMinia);

                                }
                            }
                        }
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }




            BDD_Projet_Netflix.requeteSQL("select distinct Categorie from categorisation WHERE (Categorie != '---Ajouter Catégorie---' AND Categorie != '')");
            ResultSet resultat = BDD_Projet_Netflix.getResultat();
            if (resultat != null)
            {
                while (true)
                {
                    try {
                        if (!resultat.next()) break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {


                        String cat = resultat.getString("Categorie");
                        System.out.println("La categorie: " + cat);

                        BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
                        BDD_Projet_Netflix.requeteSQL("select distinct NomFilm from categorisation WHERE (Categorie = '" +cat+ "' AND NomFilm != '')");
                        ResultSet resultatTemp = BDD_Projet_Netflix.getResultat();

                        if (resultatTemp.next())
                        {
                            Label nomCat = new Label();
                            nomCat.setText(cat);
                            nomCat.setFont(new Font(35));
                            nomCat.setTextFill(Color.PURPLE);
                            nomCat.setUnderline(true);
                            nomCat.setPadding(new Insets(40,0,10,0));

                            virtBox.getChildren().addAll(nomCat);
                            ScrollPane paneCate = new ScrollPane();
                            HBox boxCate =new HBox();

                            paneCate.setContent(boxCate);
                            paneCate.setMinHeight(310);
                            paneCate.setStyle("-fx-background-color:black");
                            paneCate.setFitToWidth(true);

                            boxCate.setFillHeight(true);
                            boxCate.setStyle("-fx-background-color:black");
                            boxCate.setSpacing(15);
                            virtBox.getChildren().addAll(paneCate);


                            BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
                            BDD_Projet_Netflix.requeteSQL("select distinct NomFilm from categorisation WHERE (Categorie = '" +cat+ "' AND NomFilm != '')");
                            ResultSet resultat2 = BDD_Projet_Netflix.getResultat();
                            if (resultat2 != null)
                            {
                                while (resultat2.next())
                                {
                                    String film = resultat2.getString("NomFilm");
                                    //System.out.println("Le fim :" + film);


                                    BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
                                    BDD_Projet_Netflix.requeteSQL("select * from films WHERE NomFilm = '" +film+ "'");
                                    ResultSet resultat3 = BDD_Projet_Netflix.getResultat();

                                    if (resultat3 != null)
                                    {
                                        while (resultat3.next())
                                        {
                                            String NomFilm = resultat3.getString("NomFilm");
                                            String miniaFilm = resultat3.getString("Miniature");

                                            //System.out.println("Le fim la:" + NomFilm);
                                            //System.out.println("Le lien la:" + miniaFilm);

                                            StackPane paneMinia = new StackPane();
                                            paneMinia.setAlignment(Pos.CENTER);

                                            Label textFilm = new Label(NomFilm);
                                            textFilm.setFont(new Font(35));
                                            textFilm.setStyle("-fx-text-fill: white");
                                            textFilm.setMaxWidth(200);
                                            textFilm.setWrapText(true);
                                            textFilm.setTextAlignment(TextAlignment.CENTER);
                                            textFilm.setAlignment(Pos.CENTER);


                                            ImageView minia = new ImageView();
                                            Image image = new Image(miniaFilm);

                                            minia.setImage(image);
                                            minia.setFitHeight(300);
                                            minia.setFitWidth(200);
                                            //minia.setStyle("-fx-opacity:50");

                                            paneMinia.setOnMouseEntered(e->{
                                                minia.setOpacity(0.3);
                                                minia.setCursor(Cursor.HAND);
                                                paneMinia.setStyle("-fx-border-color:white;-fx-border-width:4");
                                                paneMinia.getChildren().addAll(textFilm);

                                            });
                                            paneMinia.setOnMouseExited(e-> {
                                                minia.setOpacity(1);
                                                paneMinia.setStyle("-fx-border-color:purple;-fx-border-width:4");
                                                paneMinia.getChildren().removeAll(textFilm);
                                            });

                                            paneMinia.setOnMouseClicked(e ->
                                            {
                                                try {
                                                    changerPage.ouvrirFilm(NomFilm,Pseudo);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                                                stage.close();
                                            });

                                            paneMinia.getChildren().addAll(minia);
                                            paneMinia.setStyle("-fx-border-color:purple;-fx-border-width:4");
                                            boxCate.getChildren().addAll(paneMinia);

                                        }
                                    }
                                }
                            }
                        }


                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
