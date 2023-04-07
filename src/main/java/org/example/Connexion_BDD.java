package org.example;
import java.sql.*;

public class Connexion_BDD {

    private Connection connection;
    private String nomDeLaBDD = "testjps";
    private String url = "jdbc:mysql://localhost:3306/" + nomDeLaBDD;
    private String login = "root";
    private String password = "";

    public Connexion_BDD()
    {
        /**
         * ON CHARGE EN MEMOIRE LE DRIVER NECESSAIRE A L'UTILISATION DE MYSQL
         * */
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException CNFE){
            System.out.println("Impossible de charger la classe com.mysql.cj.jdbc.Driver ce qui rend impossible la connexion à mysql.");
        }

        /**
         * ON SE CONNECTE A LA BASE DE DONNEES OU NOS FILMS SONT REPERTORIES
         * */
        try {
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion correctement établie avec la base de données " + nomDeLaBDD);
        }catch (SQLException E)
        {
            System.out.println("Erreur rencontrée au moment d'établir la connextion avec la base de données " + nomDeLaBDD);
        }
    }

    /**
     * Constructeur surchargé en cas de besoin
     * (permet de mettre le nom de la BDD, le login et le password en paramètres)
     * */
    public Connexion_BDD(String _nomDeLaBDD, String _login, String _password)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException CNFE){
            System.out.println("Impossible de charger la classe com.mysql.cj.jdbc.Driver ce qui rend impossible la connexion à mysql.");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + _nomDeLaBDD, _login, _password);
            System.out.println("Connexion correctement établie avec la base de données " + _nomDeLaBDD);
        }catch (SQLException SQLE) {
            System.out.println("Erreur rencontrée au moment d'établir la connextion avec la base de données " + _nomDeLaBDD);
        }
    }
}
