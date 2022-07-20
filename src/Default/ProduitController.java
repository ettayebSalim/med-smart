/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Models.Produit;

/**
 * FXML Controller class
 *
 * @author NAD
 */
public class ProduitController implements Initializable {

    private Label labelType;
    @FXML
    private Button btnSupp;
    @FXML
    private Label labelName;
    private Produit produ;
    private SupprimerProduit supProd;
    @FXML
    private Label labelqte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Produit produit, SupprimerProduit p) {

        this.produ = produit;
        this.supProd = p;
        ///labelType.setId(produit.getNom_prod());

       // labelType.setText(produit.getId_prod());
        labelqte.setText(String.valueOf(produit.getQte_prod()));
        labelName.setText(produit.getNom_prod());
        //labelAdresse.setText(String.valueOf(produit.getQte_prod()));

    }

    @FXML
    private void Supprimer(ActionEvent event) {
       // supEtab.supprimer(etabli);
       supProd.supprimer(produ);

    }
}
