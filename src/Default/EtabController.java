/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Etablissments;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class EtabController implements Initializable {

    @FXML
    private Label labelAdresse;
    private Label labelType;
    @FXML
    private Label labelName;
    private Etablissments etabli;
    private SupprimerEtab supEtab;
    @FXML
    private Button btnSupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Etablissments etab, SupprimerEtab p) {

        this.etabli = etab;
        this.supEtab = p;
        labelAdresse.setText(etab.getAdresse());

        labelType.setText(etab.getType());
        labelName.setText(etab.getName());

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        supEtab.supprimer(etabli);

    }
}
