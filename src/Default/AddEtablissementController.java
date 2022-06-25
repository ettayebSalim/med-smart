/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Etablissments;
import Services.EtabCRUD;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class AddEtablissementController implements Initializable {

    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnSumbit;

    EtabCRUD Etb = new EtabCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Reset(ActionEvent event) {
        txtName.setText("");
        txtAdresse.setText("");
        txtType.setText("");

    }

    @FXML
    private void Sumbit(ActionEvent event) {

        if (txtName.getText().isEmpty()
                || txtAdresse.getText().isEmpty() || txtType.getText().isEmpty()) {

            
            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();
        } else if (TestText(txtName) && TestText(txtAdresse)) {
            
            Etablissments e = new Etablissments(txtName.getText(), txtAdresse.getText(), txtType.getText());

   Etb.ajouterEtab(e);
         txtName.setText("");
        txtAdresse.setText("");
        txtType.setText("");
        
  
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Etablissement Ajouté avec Succé");
            alert.showAndWait();
      
            
        }
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
    } private boolean TestName(TextField txt) {
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
    private void VerifAdresse(KeyEvent event) {
        if (txtAdresse.getText().isEmpty() == false) {
            if (TestText(txtAdresse)) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                txtAdresse.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                txtAdresse.setEffect(in);
            }
        }
    }

    @FXML
    private void VerifType(KeyEvent event) {
         if (txtType.getText().isEmpty() == false) {
            if (TestName(txtType)) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                txtType.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                txtType.setEffect(in);
            }
        }
    }

    @FXML
    private void VerifNom(KeyEvent event) {
          if (txtName.getText().isEmpty() == false) {
            if (TestName(txtName)) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                txtName.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                txtName.setEffect(in);
            }
        }
    }

}
