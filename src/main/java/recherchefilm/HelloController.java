package recherchefilm;

import ConnexionUtilisateur.ConnexionUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import ConnexionUtilisateur.AjoutUtilisateur;
import java.awt.*;
import java.io.IOException;



public class HelloController {

    @FXML
    public TextField identifiant ;
    @FXML
    public PasswordField motDePasse ;
    @FXML
    public TextField identifiantSI;
    @FXML
    public PasswordField mdpSI;
    @FXML
    public TextField identifiantUser;
    @FXML
    public TextField mdpUser;
    public Label messageErreur=new Label();
    @FXML
    private void ouvrirUserLogin(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
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
    private void verificationAdmin(ActionEvent event) throws IOException
    {
        String PseudoSaisie = identifiant.getText();
        String MdpSaisie = motDePasse.getText();
        ConnexionUtilisateur conexAdmin = new ConnexionUtilisateur();
        boolean verifAdmin = false;
        boolean mdpValable = false;
        verifAdmin = conexAdmin.connexionAdmin(PseudoSaisie);
        if(verifAdmin==false)
        {
            messageErreur.setText("Vous n'etes pas administrateur");
        }
        else
        {
            mdpValable=conexAdmin.verifPseudoMdp(PseudoSaisie,MdpSaisie);
            if(mdpValable==false)
            {
                messageErreur.setText("Mot de passe incorrect ");
            }
        }
        identifiant.setText("");
        motDePasse.setText("");
    }

    @FXML
    private void verificationUser(ActionEvent event) throws IOException
    {
        boolean auto = false;

        ConnexionUtilisateur conexUser = new ConnexionUtilisateur();

        String pseudo = identifiantUser.getText();
        String mdp = mdpUser.getText();
        auto= conexUser.verifPseudoMdp(pseudo,mdp);
        if(auto == false)
        {
            messageErreur.setText("Mot de passe incorrect");
            identifiantUser.setText("");
            mdpUser.setText("");
        }
        else
        {
            FXMLLoader fxmlLoader2 = new FXMLLoader(pageFilmApp.class.getResource("pageAccueil.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);

            Stage stage2 = new Stage();
            pageAccueil controller2 = fxmlLoader2.getController();
            controller2.setPseudo(pseudo);

            stage2.setTitle("Page d'accueil");
            stage2.setScene(scene2);
            stage2.setMaximized(true);
            Stage stage3 = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage3.close();
            stage2.show();
        }
    }

    @FXML
    private void AjoutUser(ActionEvent evenet) throws IOException
    {
        AjoutUtilisateur ajout = new AjoutUtilisateur();
        String pseudo = identifiantSI.getText();
        String mdp = mdpSI.getText();
        boolean test=false;

        test = ajout.ajouter(pseudo,mdp);
    }

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException
    {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get reference to current stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        currentStage.close();
    }

}