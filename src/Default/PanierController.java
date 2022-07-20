/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Produit;
import Services.ProduitService;
import Default.ListeProduitController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author NAD
 */
public class PanierController implements Initializable {

    @FXML
    private GridPane grid;
    private SupprimerProduit supProd;
    
    @FXML
    private AnchorPane MyPane;

   private void supprimerr(Produit prod) {
        ProduitService produit = new ProduitService();
        produit.Supprimerproduits((int) prod.getId_prod());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Produit deleted successfully");
        alert.showAndWait();

        MyPane.getChildren().clear();

        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ListeProduit.fxml"));

            AnchorPane anchorPane = cards.load();

            MyPane.getChildren().add(anchorPane);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProduitService prod = new ProduitService();

        if (prod.fetchProduit().size()>0) {
            supProd = new SupprimerProduit() {
                public void supprimer(Produit prod) {
                    supprimerr(prod);
                }
            };

        }

        int column = 1;
        int row = 0;

        try {
            for (int i = 0; i < prod.fetchProduit().size(); i++) {

                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("Produit.fxml"));

                AnchorPane anchorPane = cards.load();

                ProduitController Produitservice = cards.getController();
                Produitservice.setData(prod.fetchProduit().get(i),supProd);

                // System.out.println("hhh");
                // System.out.println(offreservice.getAllHoreca().get(i).getClass().getSimpleName());
                if (column == 2) {
                    column = 1;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));
            }

////                  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
