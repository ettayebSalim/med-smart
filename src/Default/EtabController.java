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
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author haith
 */
public class EtabController implements Initializable {

    @FXML
    private Label labelAdresse;
    @FXML
    private Label labelType;
    @FXML
    private Label labelName;
    private Etablissments etabli;
private SupprimerEtab supEtab;
private ModifierEtab modif;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Etablissments etab,SupprimerEtab p,ModifierEtab m)  {
     
            this.etabli=etab;
            this.modif=m;
            this.supEtab=p;
            labelAdresse.setText(etab.getAdresse());
         
            labelType.setText(etab.getType());
            labelName.setText(etab.getName());    
 
        }

    @FXML
    private void Supprimer(ActionEvent event) {
        supEtab.supprimer(etabli);
        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        modif.ModifierEtabl(etabli);
    }
    }

