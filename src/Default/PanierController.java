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
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class PanierController implements Initializable {

    @FXML
    private TextField tfproduit;
    @FXML
    private TextField tfdate;
    @FXML
    private Button ajouterpanier;
    @FXML
    private TableColumn<Panier, String> nomprod;
    @FXML
    private TableColumn<Panier, String> datepanier;
    @FXML
    private TableView<Panier> listpanier;

    
    
        public ObservableList<Panier> listPanier = FXCollections.observableArrayList();
        private int n = 0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection cnx2 = MyConnection.getInstance().getCnx();

        try {
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM panier");
              while (rs.next()) {
                  listPanier.add(new Panier (rs.getString("nomProd"), rs.getString("date")));
                 
                
              }
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        datepanier.setCellValueFactory(new PropertyValueFactory<>("date"));
        listpanier.setItems(listPanier);
        // TODO
    }    

    @FXML
    private void ajoutpanier(ActionEvent event) {
        
          if (tfproduit.getText().isEmpty()
                || tfdate.getText().isEmpty()) {

            
            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();
        
          
    }
          else if (TestText(tfproduit))
          
          
          {
         String nomProd = tfproduit.getText();
        String date = tfdate.getText();
        Panier p = new Panier(nomProd,date) ;
        PanierCRUD pc = new PanierCRUD();
        pc.ajouterPanier(p);
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Panier Ajouté avec Succé");
            alert.showAndWait();
                    }
      
          
    
    
}
    

    
   //  @FXML
    //public void afficherPanier() {
     //   n = 0;
      //  listpanier.getItems().removeAll(listPanier);
      //  PanierCRUD fs = new PanierCRUD();
      //  List<Panier> allPanier = fs.afficherPanier();

       // for (int i = 0; i < allPanier.size(); i++) {
          //  listPanier.add(allPanier.get(i));

      //  }
       // showTableView();

  //  }
    
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

}
