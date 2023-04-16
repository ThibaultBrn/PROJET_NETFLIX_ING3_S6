package Notation;

import example.BaseDeDonnees;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteFilm {
    private final BaseDeDonnees BDD;
    private double moyenne;
    private int NBnote;
    public NoteFilm()
    {
        BDD = new BaseDeDonnees("projet_netflix", "root", "");
        moyenne = 0;
        NBnote = 0;
    }
    public void AjouterNote(String filmANoter, double Note)
    {
        RecupNote(filmANoter);
        double NewMoyenne = ((this.moyenne)*(this.NBnote)+Note)/((double)this.NBnote+(float)1);
        double formatted_newMoyenne = Math.round(NewMoyenne * 100.0) / 100.0;
        BDD.requeteSQL("Update films Set Moyenne = '"+formatted_newMoyenne+"' where NomFilm = '"+filmANoter+"'");
        BDD.requeteSQL("Update films set NbNote = '"+(this.NBnote+1)+"' where NomFilm = '"+filmANoter+"'");
    }

    public void RecupNote(String _film)
    {
        BDD.requeteSQL("Select NbNote, Moyenne from films where NomFilm = '"+_film+"'");
        ResultSet res=BDD.getResultat();
        int nombredenote = 0;
        double Moyennne=0;
        try
        {
            while(res.next())
            {
                nombredenote=res.getInt("NbNote");
                Moyennne=res.getDouble("Moyenne");
                System.out.println("la moyenne est : '"+Moyennne+"' et le nombre de note est : '"+nombredenote+"'");
            }

        }catch (SQLException e){
            System.out.println("Impossible d'effectuer res.next() pour la note");
        }
        this.moyenne=Moyennne;
        this.NBnote=nombredenote;
        //System.out.println("la moyenne est : '"+Moyennne+"' et le nombre de note est : '"+nombredenote+"'");
    }
}