package com.example.RechercheFilm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class HelloController {

    public PasswordField motDePasse = new PasswordField();
    @FXML
    private void ouvrirUserLogin(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        currentStage.close();

    }

    @FXML
    private void ouvrirAdminLogin(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        currentStage.close();
    }
    public TextField identifiant = new TextField();
    @FXML
    private void verification(ActionEvent event) throws IOException
    {
        String texteSaisie = identifiant.getText();
        System.out.println("l'identifiant est : "+texteSaisie);
    }
}