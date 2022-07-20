/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Services.ProduitService;
import Models.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author NAD
 */
public class AjouterProduitController implements Initializable {

    //var
    ProduitService ps = new ProduitService();

    @FXML
    private TextField nom;
    @FXML
    private TextField qte;
    @FXML
    private TextField fichier;
    @FXML
    private TextField etab;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button canceladd;
    @FXML
    private Button validateadd;
    @FXML
    private AnchorPane ap_center;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Reset(ActionEvent event) {
        nom.setText("");
        qte.setText("");
        fichier.setText("");
        etab.setText("");

    }

    private boolean TestText(TextField txt) {
        Pattern p = Pattern.compile("[a-zA-Z0-9' ']*[a-zA-Z0-9' ']*");
        Matcher m = p.matcher(txt.getText());
        Matcher m1 = p.matcher(txt.getText());
        if (m.find() && m.group().equals(txt.getText()) || m1.find() && m1.group().equals(txt.getText())) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir un nom valide");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void addActon(ActionEvent event) throws SQLException {

        if (nom.getText().isEmpty()
                || qte.getText().isEmpty() || etab.getText().isEmpty() || fichier.getText().isEmpty()) {

            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();
        } else if (TestText(nom) && TestText(qte)) {

            Produit p = new Produit();

            ps.insertProduit(new Produit(nom.getText(), fichier.getText(), Integer.valueOf(qte.getText()), Integer.valueOf(etab.getText())));
            
            nom.setText("");
            qte.setText("");
            etab.setText("");
            fichier.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Produit Ajouté avec Succé");
            alert.showAndWait();

        }
    }
//      @FXML
//    private void addActon(ActionEvent event) {
//        try {
//            ps.insertProduit(new Produit(nom.getText(), fichier.getText(), Integer.valueOf(qte.getText()), Integer.valueOf(etab.getText())));
//        } catch (SQLException ex) {
//            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private boolean TestName(TextField txt) {
        Pattern p = Pattern.compile("[a-zA-Z' ']*[a-zA-Z' ']*");
        Matcher m = p.matcher(txt.getText());
        Matcher m1 = p.matcher(txt.getText());
        if (m.find() && m.group().equals(txt.getText()) || m1.find() && m1.group().equals(txt.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir un champ valide");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void VerifType(KeyEvent event) {
        if (nom.getText().isEmpty() == false) {
            if (TestName(nom)) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                nom.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                nom.setEffect(in);
            }
        }
    }

    @FXML
    private void Verifqte(KeyEvent event) {
        if (qte.getText().isEmpty() == false) {
            if (TestName(qte)) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                qte.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                qte.setEffect(in);
            }
        }
    }
}
