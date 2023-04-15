package com.example.RechercheFilm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
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

    @FXML
    public TextField identifiant=new TextField();
    public PasswordField mdp =new PasswordField();

    public Label messageErreur=new Label();
    @FXML
    private void verification(ActionEvent event) throws IOException{
        String texte = identifiant.getText();
        System.out.println(texte);
        String texte2 = mdp.getText();
        System.out.println(texte2);

        if(texte.equals("anatole")){
            messageErreur.setText("Cet utilisateur existe déjà");
        }
        else if(texte.equals("alala")){
            messageErreur.setText("Cet utilisateur n'existe pas");
        }
        else if(texte2.equals("123") && texte.equals("ala")){
            messageErreur.setText("Mot de passe incorrecte");
        }


    }


    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        currentStage.close();
    }
}