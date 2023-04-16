package ConnexionUtilisateur;

import example.BaseDeDonnees;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AjoutUtilisateur {
    public Label messageErreur=new Label();
    private BaseDeDonnees BDD2 ;
    private Scanner scanner = new Scanner(System.in);
    public AjoutUtilisateur()
    {
        BDD2=new BaseDeDonnees("projet_netflix", "root", "");
    }
    public boolean testPseudo(String _pseudo)
    {
        ResultSet res = null;
        boolean exist=true;
        BDD2.requeteSQL("Select * From utilisateur where pseudo = '"+_pseudo+"'");

        res = BDD2.getResultat();

        try{
            exist = res.next();
            System.out.println("afficher le resultat " +res.next());
        }catch (SQLException e){
            System.out.println("impossible d'effectuer la commande res.next()");
        }
        return exist;
    }
    public boolean ajouter(String _pseudo,String _mdp)
    {
        boolean test= false;
        test = testPseudo(_pseudo);
        System.out.println("le pseudo : "+_pseudo+" est "+test);
        if(test==false)
        {
            BDD2.requeteSQL("INSERT INTO utilisateur (Pseudo, MotDePasse, DroitsAdministrateur) VALUES ('"+_pseudo+"', '"+_mdp+"', '0')");
            return true;
        }
        else
        {
            messageErreur.setText("Le pseudo selectionne existe deja, veuillez en choisir un nouveau");
            return false;
        }
    }
}
