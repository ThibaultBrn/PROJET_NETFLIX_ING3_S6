package recherchefilm;

import example.BaseDeDonnees;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class rechercheFilm implements Initializable {


    private BaseDeDonnees BDD;

    private String Pseudo;

    int nbEntrees;

    @FXML
    private TextField SelectFilm;

    @FXML
    private VBox virtBox;

    @FXML Label labelNom;
    @FXML Button boutonDeco;
    @FXML Label labelNetflix;

    public void setPseudo(String pseudo)
    {
        Pseudo = pseudo;
    }


    @FXML
    public void rechercherFilm()
    {
        BDD = new BaseDeDonnees("projet_netflix", "root", "");

        System.out.println("Barre de recherche: " + SelectFilm.getText());


        nbEntrees = SelectFilm.getText().length();
        System.out.println("Longueur" + nbEntrees);

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


        while(virtBox.getChildren().size() != 3)
        {
            if(virtBox.getChildren().size() > 3)
            {
                virtBox.getChildren().remove(3);
            }
        }


        try
        {
            BDD.requeteSQL("select * from films where NomFilm LIKE '%" + SelectFilm.getText() + "%'");
            ResultSet rs = BDD.getResultat();
            if (rs != null) {
                while (rs.next())
                {

                    HBox film = new HBox();
                    film.setAlignment(Pos.CENTER_LEFT);

                    String synopsis = rs.getString("Synopsis");
                    String nomFilm = rs.getString("NomFilm");

                    film.setOnMouseClicked(e ->
                    {
                        try {
                            changerPage.ouvrirFilm(nomFilm,Pseudo);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                        stage.close();
                    });
                    Label syno = new Label();


                    film.setOnMouseExited(e ->
                    {
                        film.setStyle("-fx-border-color:none");
                        film.getChildren().remove(syno);
                    });


                    VBox.setMargin(film,new Insets(0,0,0,70));

                    ImageView minia = new ImageView();
                    Image image = new Image(rs.getString("Miniature"));

                    minia.setImage(image);
                    minia.setFitHeight(200);
                    minia.setFitWidth(150);
                    film.getChildren().addAll(minia);
                    //virtBox.getChildren().addAll(minia);


                    Label lab1 = new Label();
                    lab1.setText(rs.getString("NomFilm") +" ("+ rs.getString("Annee") +")");
                    lab1.setTextFill(Color.PURPLE);
                    lab1.setFont(new Font(30));
                    lab1.setUnderline(true);
                    lab1.setMinWidth(400);

                    Label lab2 = new Label();
                    lab2.setText("Réalisateur : "+rs.getString("PrenomRealisateur") +" "+ rs.getString("NomRealisateur"));
                    lab2.setTextFill(Color.WHITE);
                    lab2.setFont(new Font(20));

                    Label lab3 = new Label();
                    lab3.setText("Durée : "+ rs.getString("Duree") +" minutes");
                    lab3.setTextFill(Color.WHITE);
                    lab3.setFont(new Font(20));

                    VBox description = new VBox();
                    description.getChildren().addAll(lab1);
                    description.getChildren().addAll(lab2);
                    description.getChildren().addAll(lab3);
                    VBox.setMargin(lab1,new Insets(0,0,0,30));
                    VBox.setMargin(lab2,new Insets(0,0,0,30));
                    VBox.setMargin(lab3,new Insets(0,0,0,30));

                    film.getChildren().addAll(description);
                    //virtBox.getChildren().addAll(lab);

                    virtBox.getChildren().addAll(film);

                    film.setOnMouseEntered(e ->
                    {
                        film.setStyle("-fx-border-color:purple; -fx-background-color:rgba(150, 150, 150, 0.2);-fx-border-width: 2");
                        film.setCursor(Cursor.HAND);

                        syno.setText(synopsis);
                        syno.setTextFill(Color.WHITE);
                        syno.setAlignment(Pos.CENTER_RIGHT);
                        syno.setFont(new Font(15));
                        syno.setPadding(new Insets(0,20,0,0));
                        syno.setTextAlignment(TextAlignment.JUSTIFY);
                        syno.setWrapText(true);
                        film.getChildren().addAll(syno);
                    });



                    System.out.println("Nom Film: " + rs.getString("NomFilm"));
                    System.out.println("Duree Film: " + rs.getInt("Duree"));
                    System.out.println("Lien Film: " + rs.getString("Lien"));
                    System.out.println("Real Film: " + rs.getString("NomRealisateur") + " " + rs.getString("PrenomRealisateur"));
                    System.out.println("Annee Film: " + rs.getInt("Annee"));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Erreur affichage films");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() ->
        {
            labelNetflix.setOnMouseClicked(e->
            {
                try {
                    changerPage.retourMenu(e,Pseudo);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            labelNetflix.setOnMouseEntered(e->
            {
                labelNetflix.setStyle("-fx-background-color:rgba(200, 200, 200, 0.2)");
                labelNetflix.setCursor(Cursor.HAND);
            });

            labelNetflix.setOnMouseExited(e->
            {
                labelNetflix.setStyle("-fx-background-color: black");
                labelNetflix.setCursor(Cursor.HAND);
            });
            rechercherFilm();
        });
    }

}