package com.example.RechercheFilm;

import java.sql.*;

public class SQLPart {
    private static Connection cn;

    public static ResultSet recupererData(String element)
    {

        try
        {
            Statement stmt = cn.createStatement();
            String sql = "select * from films where NomFilm LIKE '%" + element+ "%'";

            System.out.println("Avant SQL");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Apres SQL");
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