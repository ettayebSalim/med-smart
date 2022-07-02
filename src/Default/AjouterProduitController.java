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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private void addActon(ActionEvent event) {
        try {
            ps.insertProduit(new Produit(nom.getText(), fichier.getText(), Integer.valueOf(qte.getText()), Integer.valueOf(etab.getText())));
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
