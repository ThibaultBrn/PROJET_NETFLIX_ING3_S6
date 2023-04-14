package ConnexionUtilisateur;

import org.example.BaseDeDonnees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ConnexionUtilisateur {

    private Scanner scanner = new Scanner(System.in);
    private BaseDeDonnees BDD;
    public ConnexionUtilisateur()
    {
        BDD = new BaseDeDonnees("projet_netflix", "root", "");
    }

    public void loginUtilisateur()
    {
        int choixMenu;
        do {
            System.out.println("1 : Se co en tant qu'utilisateur || 2 : Se co en tant qu'admin || 3 : Quitter");
            choixMenu = scanner.nextInt();
            if(choixMenu == 1)
            {

            }
            else if(choixMenu == 2)
            {

            }
            else
            {
                System.out.println("Choisissez un nombre entre 1 et 3");
            }
        }while(choixMenu != 3);
    }

    public void connexionUtilisateurSimple()
    {
        System.out.println("Entrez votre pseudo :");

    }

    public boolean isAdministrateur(String pseudo)
    {
        ResultSet res = null;
        boolean isAdmin = false;
        BDD.requeteSQL("SELECT DroitsAdministrateur FROM utilisateur WHERE Pseudo = '" + pseudo + "'");
        res = BDD.getResultat();
        try{
            isAdmin = res.getBoolean("DroitsAdministrateur");
        }catch(SQLException e){
            System.out.println("Pseudo introuvable dans la base de donnees");
        }
        if(isAdmin)
        {
            System.out.println("Vous etes bien un administrateur");
            try{res.close();}catch (SQLException E){System.out.println("Impossible de fermer le registre de resultat dans la methode boolean isUtilisateur()");}
            return true;
        }
        else
        {
            System.out.println("Vous n'etes pas un administrateur");
            return false;
        }
    }
}
