package Notation;

import example.BaseDeDonnees;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteFilm {
    private BaseDeDonnees BDD;
    float moyenne;
    int NBnote;
    public NoteFilm()
    {
        BDD = new BaseDeDonnees("projet_netflix", "root", "");
        moyenne = 0;
        NBnote = 0;
    }
    public void AjouterNote()
    {
        float NewMoyenne=0;
        float Note=3;
        String filmANoter="Avatar";
        RecupNote(filmANoter);
        NewMoyenne = (this.moyenne+(float)Note)/((float)this.NBnote+(float)1);
        BDD.requeteSQL("Update films Set Moyenne = '"+NewMoyenne+"' where NomFilm = '"+filmANoter+"'");
    }

    public void RecupNote(String _film)
    {
        ResultSet res=null;
        BDD.requeteSQL("Select Moyenne from films where NomFilm = '"+_film+"'");
        res= BDD.getResultat();
        try
        {
            res.next();
        }catch (SQLException e){
            System.out.println("Impossible d'effectuer res.next() pour la moyenne");
        }
        try
        {
            moyenne=res.getFloat("Moyenne");
        }catch (SQLException ev){
            System.out.println("Impossible d'effectuer res.getInt()");
        }
        BDD.requeteSQL("Select NbNote from films where NomFilm = '"+_film+"'");
        res=BDD.getResultat();
        try
        {
            res.next();
        }catch (SQLException e){
            System.out.println("Impossible d'effectuer res.next() pour la note");
        }
        try
        {
            moyenne=res.getInt("NbNote");
        }catch (SQLException ev){
            System.out.println("Impossible d'effectuer res.getInt()");
        }
    }
}
