package ConnexionUtilisateur;

import example.BaseDeDonnees;

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



    public boolean verifPseudoMdp(String _pseudo, String _mdp)
    {
        String mdpValable="";
        if(_pseudo.equals("")==false)
        {
            BDD.requeteSQL("Select MotDePasse From utilisateur where pseudo = '"+_pseudo+"'");
            ResultSet res= BDD.getResultat();
            try
            {
                res.next();
                mdpValable = res.getString("MotDePasse");
            }catch (SQLException e){System.out.println("Impossible de recuperer le mdp");}
            System.out.println("le mdp associé est : "+mdpValable);
            if(_mdp.equals(mdpValable))
            {
                System.out.println("Mot de passe correct");
                return true;
            }
            else
            {
                System.out.println("Mot de passe incorrect");
                return false;
            }
        }
        else
        {
            return false;
        }
    }



    public boolean connexionAdmin(String _pseudo)
    {
        String pseudo;
        boolean adminValidation = false;
        adminValidation=isAdministrateur(_pseudo);
        return adminValidation;
    }

    public boolean isAdministrateur(String pseudo)
    {
        ResultSet res = null;
        boolean isAdmin = false;
        BDD.requeteSQL("SELECT DroitsAdministrateur FROM utilisateur WHERE Pseudo = '" + pseudo + "';");
        res = BDD.getResultat();

        try{
            res.next();
            isAdmin = res.getBoolean("DroitsAdministrateur");

        }catch(SQLException e){
            try{res.close();}catch (SQLException E){System.out.println("Impossible de fermer le registre de resultat dans la methode boolean isUtilisateur()");}
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
