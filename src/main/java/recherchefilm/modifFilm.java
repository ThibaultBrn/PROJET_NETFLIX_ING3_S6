package recherchefilm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class modifFilm implements Initializable {

    @FXML private TextField nomFilm;
    @FXML private TextField dureeFilm;
    @FXML private TextField lienFilm;
    @FXML private TextField synopsisFilm;
    @FXML private TextField nomReal;
    @FXML private TextField prenomReal;
    @FXML private TextField anneeSortie;
    @FXML private TextField miniature;
    @FXML private ChoiceBox selectCate;
    @FXML private HBox ajoutCate;
    @FXML private TextField nouvelleCate;
    @FXML private ChoiceBox selectFilm;


    @FXML private Button boutonModif;

    protected void ajouterFilm(MouseEvent e)
    {

        System.out.println("Duree:" + Integer.parseInt(dureeFilm.getText()));
        System.out.println("Lien:" + lienFilm.getText());
        System.out.println("Synopsis:" + synopsisFilm.getText());
        System.out.println("Nom Real:" + nomReal.getText());
        System.out.println("Prenom Real:" + prenomReal.getText());
        System.out.println("Annee:" + Integer.parseInt(anneeSortie.getText()));
        System.out.println("Miniature:" + miniature.getText());
        System.out.println("Categorie :" + selectCate.getValue().toString());


        if (Objects.equals(selectCate.getValue().toString(), "---Ajouter Catégorie---") && selectFilm.getValue() != null)
        {
            SQLPart.addCategories(nouvelleCate.getText(),"");
            SQLPart.miseAJour(nomFilm.getText().replace("'","''"),Integer.parseInt(dureeFilm.getText()),lienFilm.getText(),synopsisFilm.getText().replace("'","''"),nomReal.getText().replace("'","''"),prenomReal.getText().replace("'","''"), Integer.parseInt(anneeSortie.getText()), miniature.getText(),selectFilm.getValue().toString(),nouvelleCate.getText());
            selectCate.setValue(nouvelleCate.getText());
        }
        else if(selectFilm.getValue() != null)
        {
            SQLPart.miseAJour(nomFilm.getText().replace("'","''"),Integer.parseInt(dureeFilm.getText()),lienFilm.getText(),synopsisFilm.getText().replace("'","''"),nomReal.getText().replace("'","''"),prenomReal.getText().replace("'","''"), Integer.parseInt(anneeSortie.getText()), miniature.getText(),selectFilm.getValue().toString(),selectCate.getValue().toString());
        }
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow(); // get reference to current stage
        currentStage.close();
    }

    void recupCate()
    {
        selectCate.getItems().removeAll();
        selectCate.getItems().clear();

        ResultSet rs = SQLPart.getCategories();
        if (rs != null) {
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    selectCate.getItems().addAll(rs.getString("Categorie"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            nouvelleCate.setVisible(false);

        }
    }

    void recupFilms()
    {
        selectFilm.getItems().removeAll();
        selectFilm.getItems().clear();

        ResultSet rs = SQLPart.getFilms();

        if (rs != null) {
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    selectFilm.getItems().addAll(rs.getString("NomFilm"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        recupFilms();
        recupCate();

        boutonModif.setOnMouseClicked(e->ajouterFilm(e));



        selectCate.setOnAction(e-> {


            if (selectCate.getValue() !=null)
            {
                if (Objects.equals(selectCate.getValue().toString(), "---Ajouter Catégorie---"))
                {
                    System.out.println("Changement");

                    nouvelleCate.setVisible(true);


                }
                else
                {
                    nouvelleCate.setVisible(false);
                }
            }

        });

        selectFilm.setOnAction(e->
        {
            ResultSet rs = SQLPart.getInfosFilm(selectFilm.getValue().toString());

            if (selectFilm.getValue() !=null)
            {
                try {

                    while (rs.next())
                    {
                        nomFilm.setText(rs.getString("NomFilm"));
                        dureeFilm.setText(rs.getString("Duree"));
                        lienFilm.setText(rs.getString("Lien"));
                        synopsisFilm.setText(rs.getString("Synopsis"));
                        nomReal.setText(rs.getString("NomRealisateur"));
                        prenomReal.setText(rs.getString("PrenomRealisateur"));
                        anneeSortie.setText(rs.getString("Annee"));
                        miniature.setText(rs.getString("Miniature"));

                        ResultSet rs2 = SQLPart.getCategorieFilm(selectFilm.getValue().toString());
                        rs2.next();
                        selectCate.setValue(rs2.getString("Categorie"));
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}
