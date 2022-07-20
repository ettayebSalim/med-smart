/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Default;

import Models.Fichier;
import Models.Panier;
import Services.FichierService;
import Services.PanierCRUD;
import Utiles.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21622
 */

public class EditPanierController implements Initializable {

    @FXML
    private TableColumn<Panier, String> nomprod;
    @FXML
    private TableColumn<Panier, String> datepanier;
    
    private final ObservableList<Panier> editPaniers = FXCollections.observableArrayList();
    @FXML
    private TableView<Panier> editpanier;
    
      
        public ObservableList<Panier> listPanier = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.@RE
     * create new class java intelij
     * creajava.
     * 
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     
    public void editAllPaniers() {
  
        Connection cnx2 = MyConnection.getInstance().getCnx();

    

        try {
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM panier");
              while (rs.next()) {
                  listPanier.add(new Panier (rs.getString("nomProd"), rs.getString("date")));
                 
                
              }
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      

      
    }
     //Delete a row from TableView 
    @FXML
    public void deleteRowTabView(ActionEvent e) {
        int SelectedId = editpanier.getSelectionModel().getSelectedIndex();
        editpanier.getItems().remove(SelectedId);
    }

   public void passPaniertoEdit(ObservableList<Panier> x) throws IOException {
      
        editPaniers.addAll(x); 
        
        System.out.println(editPaniers); 
        
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        datepanier.setCellValueFactory(new PropertyValueFactory<>("date"));
   
        editpanier.setItems(editPaniers);
    }    
    
}
