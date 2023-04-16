package recherchefilm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import example.BaseDeDonnees;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class pageFilm implements Initializable{

    @FXML
    private WebView webView;

    @FXML
    private VBox virtualbox;

    public String nomFilm;

    private final Button listeLecture = new Button();
    boolean present = false;
    private String Pseudo;

    public void setPseudo(String pseudo)
    {
        Pseudo = pseudo;
    }
    public void retourRecherche() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(pageAccueil.class.getResource("pageAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Stage stage = new Stage();

        stage.setTitle("pageRecherche");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public void setNomFilm(String nNom) {
        nomFilm = nNom;
    }

    void majBouton()
    {
        try
        {
            BaseDeDonnees BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
            BDD_Projet_Netflix.requeteSQL("select NomFilm from listelecture WHERE Pseudo = '" + Pseudo + "' AND NomFilm = '" + nomFilm + "'");
            ResultSet resultatListe = BDD_Projet_Netflix.getResultat();

            if (!resultatListe.next()) {
                System.out.println("Le film n'est pas dans la liste");
                listeLecture.setText("Ajouter à ma liste de lecture");
                present = false;
            } else {
                System.out.println("Le film est dans la liste");
                listeLecture.setText("Retirer de ma liste de lecture");
                present = true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {

            System.out.println("Page lancee");
            System.out.println("Nom f" + nomFilm);
            WebEngine engine = webView.getEngine();



            BaseDeDonnees BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
            BDD_Projet_Netflix.requeteSQL("select * from films where NomFilm ='" + nomFilm+ "'");
            ResultSet rs = BDD_Projet_Netflix.getResultat();
            if(rs!=null)
            {
                try {
                    rs.next();
                    System.out.println("Nom" + rs.getString("NomFilm"));
                    System.out.println("Date" + rs.getString("Annee"));
                    System.out.println("Synopsis" + rs.getString("Synopsis"));
                    System.out.println("Duree" + rs.getString("Duree"));

                    String lien = rs.getString("Lien");
                    lien = lien.replace("watch?v=","embed/");
                    System.out.println("Le lien:" + lien);
                    engine.load(lien+"?autoplay=1&controls=0");

                    URI link = new URI(lien+"?autoplay=1&controls=0");

                    HBox infos = new HBox();
                    ImageView minia = new ImageView();
                    Image image = new Image(rs.getString("Miniature"));

                    minia.setImage(image);
                    minia.setFitHeight(800);
                    minia.setFitWidth(600);
                    infos.getChildren().addAll(minia);


                    Label lab1 = new Label();
                    lab1.setText(rs.getString("NomFilm") +" ("+ rs.getString("Annee") +")");
                    lab1.setTextFill(Color.PURPLE);
                    lab1.setFont(new Font(50));
                    lab1.setUnderline(true);

                    Label lab2 = new Label();
                    lab2.setText("Réalisateur : "+rs.getString("PrenomRealisateur") +" "+ rs.getString("NomRealisateur"));
                    lab2.setTextFill(Color.WHITE);
                    lab2.setFont(new Font(15));

                    Label lab3 = new Label();
                    lab3.setText("Durée : "+ rs.getString("Duree") +" minutes");
                    lab3.setTextFill(Color.WHITE);
                    lab3.setFont(new Font(15));

                    VBox description = new VBox();
                    description.getChildren().addAll(lab1);
                    description.getChildren().addAll(lab2);
                    description.getChildren().addAll(lab3);
                    VBox.setMargin(lab1,new Insets(0,0,0,30));
                    VBox.setMargin(lab2,new Insets(0,0,0,30));
                    VBox.setMargin(lab3,new Insets(0,0,0,30));

                    Label syno = new Label();
                    String synopsis = rs.getString("Synopsis");

                    syno.setText(synopsis);
                    syno.setFont(new Font(25));
                    syno.setTextFill(Color.WHITE);
                    syno.setAlignment(Pos.CENTER_RIGHT);
                    syno.setPadding(new Insets(40,80,0,80));
                    syno.setWrapText(true);
                    syno.setTextAlignment(TextAlignment.JUSTIFY);

                    syno.setText(synopsis);
                    description.getChildren().addAll(syno);
                    infos.getChildren().addAll(description);


                    virtualbox.getChildren().addAll(infos);

                    Button lancerLecture = new Button();
                    lancerLecture.setText("Lancer la lecture");

                    lancerLecture.setAlignment(Pos.CENTER);
                    lancerLecture.setTextFill(Color.WHITE);
                    lancerLecture.setStyle("-fx-border-color:purple; -fx-background-color:black;-fx-text-fill: purple;-fx-border-width: 2");
                    lancerLecture.setFont(new Font(40));
                    lancerLecture.setLayoutX(80);
                    lancerLecture.setLayoutY(40);

                    lancerLecture.setOnMouseClicked(e ->
                    {
                        try {
                            java.awt.Desktop.getDesktop().browse(link);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    lancerLecture.setOnMouseEntered(e ->
                    {
                        lancerLecture.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
                        lancerLecture.setCursor(Cursor.HAND);

                    });

                    lancerLecture.setOnMouseExited(e -> lancerLecture.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));


                    majBouton();

                    listeLecture.setOnMouseClicked(e ->
                    {
                        BaseDeDonnees base = new BaseDeDonnees("projet_netflix", "root", "");
                        if(present)
                        {
                            base.requeteSQL("DELETE from listelecture WHERE Pseudo ='" + Pseudo + "' AND NomFilm = '" + nomFilm + "'");
                        }
                        else
                        {
                            base.requeteSQL("INSERT INTO `listelecture` (`Pseudo`, `NomFilm`) VALUES ('"+Pseudo+"', '"+nomFilm+"')");
                        }
                        majBouton();

                    });

                    listeLecture.setOnMouseEntered(e ->
                    {
                        listeLecture.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
                        listeLecture.setCursor(Cursor.HAND);

                    });

                    listeLecture.setOnMouseExited(e -> listeLecture.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));



                    listeLecture.setAlignment(Pos.CENTER);
                    listeLecture.setTextFill(Color.WHITE);
                    listeLecture.setStyle("-fx-border-color:purple; -fx-background-color:black;-fx-text-fill: purple;-fx-border-width: 2");
                    listeLecture.setFont(new Font(40));
                    listeLecture.setLayoutX(600);
                    listeLecture.setLayoutY(40);



                    AnchorPane anchPane = new AnchorPane();

                    anchPane.getChildren().addAll(lancerLecture);
                    anchPane.getChildren().addAll(listeLecture);
                    description.getChildren().addAll(anchPane);


                } catch (SQLException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }

        });

    }


    @FXML
    public void retourMenu(ActionEvent event) throws IOException {
        webView.getEngine().load(null); // arrête la lecture de la vidéo
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        currentStage.close();
    }
}
