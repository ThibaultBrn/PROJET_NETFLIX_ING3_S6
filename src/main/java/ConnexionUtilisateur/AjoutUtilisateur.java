package ConnexionUtilisateur;

import org.example.BaseDeDonnees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AjoutUtilisateur {
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
    public boolean ajouter()
    {
        String pseudo ="";
        String mdp = "";
        boolean test= false;
        System.out.println("Saisir le nom d'utilisateur ");
        pseudo= scanner.nextLine();
        test = testPseudo(pseudo);
        System.out.println("est ce que le pseudo existe ? : "+test);
        if(test==false)
        {
            System.out.println("Saisir un mot de passe");
            mdp = scanner.nextLine();
            BDD2.requeteSQL("INSERT INTO utilisateur (Pseudo, MotDePasse, DroitsAdministrateur) VALUES ('"+pseudo+"', '"+mdp+"', '0')");
            return true;
        }
        else
        {
            System.out.println("Le pseudo selectionne existe deja, veuillez en choisir un nouveau");
            return false;
        }
    }
}
