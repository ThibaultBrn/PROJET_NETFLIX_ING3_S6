package example;

import java.sql.*;

public class BaseDeDonnees {

    private final String nomDeLaBDD;
    private Connection connexionALaBaseDeDonnees;
    private Statement stmt = null;
    private ResultSet resultat = null;

    public BaseDeDonnees(String _nomDeLaBDD, String login, String mdp) {
        nomDeLaBDD = _nomDeLaBDD;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException CNFE) {
            System.out.println("Impossible de charger la classe com.mysql.cj.jdbc.Driver ce qui rend impossible la connexion à mysql.");
        }
        /**
         * ON SE CONNECTE A LA BASE DE DONNEES OU NOS FILMS SONT REPERTORIES
         * */
        try {
            connexionALaBaseDeDonnees = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + _nomDeLaBDD, login, mdp);
            System.out.println("Connexion correctement établie avec la base de données " + _nomDeLaBDD);
        } catch (SQLException SQLE) {
            System.out.println("Erreur rencontrée au moment d'établir la connextion avec la base de données " + _nomDeLaBDD);
        }
        /**
         * ON CREE UN STATEMENT AFIN DE POUVOIR RECUPERER DES REQUETES
         * */
        try {
            stmt = connexionALaBaseDeDonnees.createStatement();
        } catch (SQLException SQLE) {
            System.out.println("Echec lors de la création d'un objet de type statement pour la BDD " + nomDeLaBDD);
        }
    }

    public void requeteSQL(String requete) {
        try {
            if (stmt.execute(requete)) {
                resultat = stmt.getResultSet();
            }
            System.out.println("La requete " + requete + "a été exéctuée avec succès pour la BDD " + nomDeLaBDD);
        } catch (SQLException SQLE) {
            System.out.println("Echec lors de l'execution de la requete " + requete + "pour la BDD " + nomDeLaBDD);
        }
    }

    public ResultSet getResultat() {
        return resultat;
    }
}

