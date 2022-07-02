/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Models.Produit;

/**
 * FXML Controller class
 *
 * @author NAD
 */
public class SuppproduitController implements Initializable {

    @FXML
    private Button suppbutton;
    @FXML
    private TextField nom_supp;
    ProduitService ps = new ProduitService();   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void delActon(ActionEvent event) {
        ps.Supprimerproduits(Integer.valueOf(nom_supp.getText()));
    }

    void supprimer(Produit produ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
 *
 * @author haith
 */
public interface SupprimerProduit {
    
    public void supprimer(Produit produ);
    
}
}
