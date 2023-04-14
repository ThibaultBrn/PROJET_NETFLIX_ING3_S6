package com.example.testjavafx;

import java.sql.*;
public class SQLPart {
    private static Connection cn;

    public static void ajoutDonnee(String nom, int duree, String Lien, String synopsis, String nomRea, String prenomRea, int annee, String miniature)
    {
        connectionSQL();
        try
        {
            Statement st = cn.createStatement();
            String request = "INSERT INTO films VALUES ('"+nom+"',"+duree+",'"+Lien+"','"+synopsis+"','"+nomRea+"','"+prenomRea+"',"+annee+", '"+miniature+"')";
            //st.executeUpdate("INSERT INTO films VALUES ('Fight Club',139,'ece.fr','Combat secret','Fincher','David', '1999')");
            System.out.println("Requete: " + request);
            st.executeUpdate(request);
        }
        catch(SQLException e)
        {
            System.out.println("Probleme sql ajout");
            e.printStackTrace();
        }

    }
    public static ResultSet getCategories()
    {

        connectionSQL();
        try
        {
            Statement stmt = cn.createStatement();
            String sql = "SELECT DISTINCT Categorie FROM categorisation";
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println("Erreur recup data SQL");
        }
        return null;
    }

    public static void addCategories(String categorie, String film)
    {
        connectionSQL();
        try
        {
            Statement stmt = cn.createStatement();

            //String sql = "INSERT INTO `categorisation` (`NomFilm`, `Categorie`) VALUES ('Moi', 'Science-Fiction')";
            String sql = "INSERT INTO categorisation VALUES('"+film+"','"+categorie+"')";
            System.out.println("REQUETE AJOUT : " + sql);
            stmt.executeUpdate(sql);

        }
        catch (SQLException e)
        {
            System.out.println("Erreur ajout catégorie");
            e.printStackTrace();
        }

    }

    public static void connectionSQL() {

        try {
            System.out.println("CONNECTION A LA SQL");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_netflix", "root", "");
        } catch(SQLException e)
        {
            System.out.println("Problème SQL");
        }

        System.out.println("FIN CONNECTION A LA SQL");

    }
}