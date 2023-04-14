package com.example.testjavafx;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AjoutFilm implements Initializable {

    @FXML private TextField nomFilm;
    @FXML private TextField dureeFilm;
    @FXML private TextField lienFilm;
    @FXML private TextField synopsisFilm;
    @FXML private TextField nomReal;
    @FXML private TextField prenomReal;
    @FXML private TextField anneeSortie;
    @FXML private TextField miniature;
    @FXML private ChoiceBox selectCate;


    @FXML
    protected void ajouterFilm()
    {

        System.out.println("Duree:" + Integer.parseInt(dureeFilm.getText()));
        System.out.println("Lien:" + lienFilm.getText());
        System.out.println("Synopsis:" + synopsisFilm.getText());
        System.out.println("Nom Real:" + nomReal.getText());
        System.out.println("Prenom Real:" + prenomReal.getText());
        System.out.println("Annee:" + Integer.parseInt(anneeSortie.getText()));
        System.out.println("Miniature:" + miniature.getText());
        System.out.println("Categorie :" + selectCate.getValue().toString());


        SQLPart.ajoutDonnee(nomFilm.getText().replace("'","''"),Integer.parseInt(dureeFilm.getText()),lienFilm.getText(),synopsisFilm.getText().replace("'","''"),nomReal.getText().replace("'","''"),prenomReal.getText().replace("'","''"), Integer.parseInt(anneeSortie.getText()), miniature.getText());
        SQLPart.addCategories(selectCate.getValue().toString(),nomFilm.getText().replace("'","''"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ResultSet rs = SQLPart.getCategories();
        if (rs != null) {
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    selectCate.getItems().addAll(rs.getString("Categorie"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
