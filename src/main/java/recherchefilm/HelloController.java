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

}