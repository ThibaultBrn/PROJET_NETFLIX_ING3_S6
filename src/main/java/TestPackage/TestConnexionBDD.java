package TestPackage;
import example.BaseDeDonnees;

public class TestConnexionBDD {
    public static void main(String[] args)
    {
        //BaseDeDonnees BDD1 = new BaseDeDonnees("projet_netflix", "root", "");
        //BDD1.requeteSQL("INSERT INTO films (Nom, Synopsis, Categories, LIEN) VALUES ('Fight Club', 'Combat secret', 'Action', 'ece.fr')");
        ConnexionUtilisateur coUtilisateur = new ConnexionUtilisateur();
        coUtilisateur.loginUtilisateur();
    }
}
