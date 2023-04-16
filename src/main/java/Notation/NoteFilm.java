package Notation;

import example.BaseDeDonnees;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteFilm {
    private BaseDeDonnees BDD;
    private float moyenne;
    private int NBnote;
    public NoteFilm()
    {
        BDD = new BaseDeDonnees("projet_netflix", "root", "");
        moyenne = 0;
        NBnote = 0;
    }
    public void AjouterNote(String filmANoter, double Note)
    {
        double NewMoyenne=0;
        RecupNote(filmANoter);
        NewMoyenne = ((this.moyenne)*(this.NBnote)+Note)/((double)this.NBnote+(float)1);
        BDD.requeteSQL("Update films Set Moyenne = '"+NewMoyenne+"' where NomFilm = '"+filmANoter+"'");
        BDD.requeteSQL("Update films set NbNote = '"+(this.NBnote+1)+"' where NomFilm = '"+filmANoter+"'");
    }

    public void RecupNote(String _film)
    {
        ResultSet res=null;
        BDD.requeteSQL("Select NbNote, Moyenne from films where NomFilm = '"+_film+"'");
        res=BDD.getResultat();
        try
        {
            while(res.next())
            {
                this.NBnote=res.getInt("NbNote");
                this.moyenne=res.getFloat("Moyenne");
            }

        }catch (SQLException e){
            System.out.println("Impossible d'effectuer res.next() pour la note");
        }

        System.out.println("la moyenne est : '"+this.moyenne+"' et le nombre de note est : '"+this.NBnote+"'");
    }
}
