package com.example.recherchefilm;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rechercheFilm {


    int nbEntrees;

    @FXML
    private TextField SelectFilm;

    @FXML
    private VBox virtBox;

    @FXML
    public void rechercherFilm()
    {
        System.out.println("Barre de recherche: " + SelectFilm.getText());


        nbEntrees = SelectFilm.getText().length();
        System.out.println("Longueur" + nbEntrees);


        while(virtBox.getChildren().size() != 2)
        {
            if(virtBox.getChildren().size() > 2)
            {
                virtBox.getChildren().remove(2);
            }
        }


        try
        {
            ResultSet rs = SQLPart.recupererData(SelectFilm.getText());
            if (rs != null) {
                while (rs.next())
                {

                    HBox film = new HBox();
                    String lien = rs.getString("Lien");
                    film.setAlignment(Pos.CENTER_LEFT);
                    URI link = new URI(lien);

                    film.setOnMouseClicked(e ->
                    {
                        try {
                            java.awt.Desktop.getDesktop().browse(link);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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




                    System.out.println("Nom Film: " + rs.getString("NomFilm"));
                    System.out.println("Duree Film: " + rs.getInt("Duree"));
                    System.out.println("Lien Film: " + rs.getString("Lien"));
                    System.out.println("Real Film: " + rs.getString("NomRealisateur") + " " + rs.getString("PrenomRealisateur"));
                    System.out.println("Annee Film: " + rs.getInt("Annee"));
                }
            }
        }
        catch (SQLException | URISyntaxException e)
        {
            System.out.println("Affichage films");
        }

    }
}