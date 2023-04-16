package com.example.testjavafx;

import java.sql.*;
public class SQLPart {
    private static Connection cn;


    public static void miseAJour(String nom, int duree, String Lien, String synopsis, String nomRea, String prenomRea, int annee, String miniature, String filmAvant, String cateAvant)
    {
        connectionSQL();
        try
        {
            Statement st = cn.createStatement();
            String request = "UPDATE films SET NomFilm = '"+nom+"', Duree = "+duree+",Lien ='"+Lien+"',Synopsis='"+synopsis+"',NomRealisateur = '"+nomRea+"',PrenomRealisateur ='"+prenomRea+"', Annee ="+annee+", Miniature ='"+miniature+"' WHERE NomFilm ='"+filmAvant+"'";
            System.out.println("Requete: " + request);
            st.executeUpdate(request);

            request = "UPDATE categorisation SET NomFilm = '"+nom+"', Categorie = '"+cateAvant+"' WHERE NomFilm ='"+filmAvant+"'";
            System.out.println("Requete: " + request);
            st.executeUpdate(request);
        }
        catch(SQLException e)
        {
            System.out.println("Probleme sql ajout");
            e.printStackTrace();
        }

    }
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

    public static ResultSet getFilms()
    {

        connectionSQL();
        try
        {
            Statement stmt = cn.createStatement();
            String sql = "SELECT DISTINCT NomFilm FROM films";
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println("Erreur recup data SQL");
        }
        return null;
    }

    public static ResultSet getInfosFilm(String film)
    {
        connectionSQL();
        try
        {
            Statement stmt = cn.createStatement();
            String sql = "SELECT * FROM films WHERE NomFilm = '"+film+"'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Requete:"+sql );
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println("Erreur recup data SQL");
        }
        return null;
    }

    public static ResultSet getCategorieFilm(String film)
    {

        connectionSQL();
        try
        {
            Statement stmt = cn.createStatement();
            String sql = "SELECT * FROM categorisation where NomFilm = '"+film+"'";
            System.out.println("Requete:"+sql );
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println("Erreur recup data SQL");
        }
        return null;
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