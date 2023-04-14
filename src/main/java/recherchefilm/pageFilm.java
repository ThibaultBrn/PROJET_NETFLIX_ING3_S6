package recherchefilm;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import example.BaseDeDonnees;
import javafx.stage.Stage;
import java.io.IOException;
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

    public void retourRecherche() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(rechercheFilm.class.getResource("rechercheFilm.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {

            System.out.println("Page lancee");
            System.out.println("Nom f" + nomFilm);
            WebEngine engine = webView.getEngine();



           example.BaseDeDonnees BDD_Projet_Netflix = new BaseDeDonnees("projet_netflix", "root", "");
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
                                retourRecherche();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            engine.load(null);
                            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                            stage.close();

                        });

                        lancerLecture.setOnMouseEntered(e ->
                        {
                            lancerLecture.setStyle("-fx-border-color:white;-fx-text-fill: white;-fx-border-width: 2; -fx-background-color:black");
                            lancerLecture.setCursor(Cursor.HAND);

                        });

                        lancerLecture.setOnMouseExited(e -> lancerLecture.setStyle("-fx-border-color:purple;-fx-text-fill: purple;-fx-border-width: 2; -fx-background-color:black"));

                        AnchorPane anchPane = new AnchorPane();

                        anchPane.getChildren().addAll(lancerLecture);
                        description.getChildren().addAll(anchPane);


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
            }

        });
    }

}