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
        int choixAjout=0;
        do {
            System.out.println("1 : Se co en tant qu'utilisateur || 2 : Se co en tant qu'admin || 3 : Quitter");
            choixMenu = scanner.nextInt();
            scanner.nextLine();
            if(choixMenu == 1)
            {
                do {
                    System.out.println("1: Se connecter avec un compte existant || 2: Ajouter un compte || 3: Quitter");
                    choixAjout=scanner.nextInt();
                    scanner.nextLine();
                    if(choixAjout==1)
                    {
                        connexionUtilisateurSimple();
                    }
                    else if (choixAjout==2)
                    {
                        ajouterUnUtilisateur();
                    }
                    else
                    {
                        System.out.println("Choisissez un nombre entre 1 et 3");
                    }
                }while(choixAjout !=3);


            }
            else if(choixMenu == 2)
            {
                connexionAdmin();
            }
            else
            {
                System.out.println("Choisissez un nombre entre 1 et 3");
            }
        }while(choixMenu != 3);
    }

    public void connexionUtilisateurSimple()
    {
        String pseudo;
        String mdp;
        boolean validation=false;

        do {
            System.out.println("Entrez votre pseudo :");
            pseudo = scanner.nextLine();
            System.out.println("Entrez votre mdp");
            mdp=scanner.nextLine();
            validation=this.verifPseudoMdp(pseudo,mdp);
        }while(validation==false);

    }
    public boolean verifPseudoMdp(String _pseudo, String _mdp)
    {

        String mdpValable="";
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
            System.out.println("Mot de passe correcte");
            return true;
        }
        else
        {
            System.out.println("Mot de passe incorrect");
            return false;
        }
    }

    public void ajouterUnUtilisateur()
    {
        boolean utilisateurAjoute = false;
        AjoutUtilisateur ajout = new AjoutUtilisateur();

        do {
            utilisateurAjoute=ajout.ajouter();
        }while (utilisateurAjoute == false);
    }

    public void connexionAdmin()
    {
        String pseudo;
        System.out.println("Entrez votre pseudo :");
        pseudo = scanner.nextLine();
        isAdministrateur(pseudo);
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
