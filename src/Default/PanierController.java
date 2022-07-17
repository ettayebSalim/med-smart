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
import java.sql.PreparedStatement;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class PanierController implements Initializable {
@FXML
    private BorderPane BplistPanier;


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
    
    
    public BorderPane borderpanemain;
        PreparedStatement ps = null;

    private MenuController menucontroller;

    
        public ObservableList<Panier> listPanier = FXCollections.observableArrayList();
        public ObservableList<Panier> listPanierTopass = FXCollections.observableArrayList();

        private int n = 0;
    @FXML
    private Button editmode;
    
    @FXML
    private TableColumn<Panier, Integer> id;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllPaniers();
     
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
            getAllPaniers();
                    }
      
          
    
    
}
    public void getAllPaniers () {
           Connection cnx2 = MyConnection.getInstance().getCnx();

        try {
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM panier");
              while (rs.next()) {
                  listPanier.add(new Panier (rs.getInt("id"),rs.getString("nomProd"), rs.getString("date")));
                 
                
              }
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listpanier.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        datepanier.setCellValueFactory(new PropertyValueFactory<>("date"));
        listpanier.setItems(listPanier);
    
}
    @FXML
    public void deletePanier (ActionEvent event) {
       int selectedPanier = listpanier.getSelectionModel().getSelectedIndex();
       System.out.println(id.getCellData(selectedPanier).toString());
      try {
           
             String query = "delete from panier where id ="+id.getCellData(selectedPanier).toString();   
             Connection cnx2 = MyConnection.getInstance().getCnx();
             ps = cnx2.prepareStatement(query);
             ps.execute();
             JOptionPane.showMessageDialog(null, "Panier supprimé avec succés");
                        getAllPaniers() ;

         
      }
       catch  (SQLException ex) {
                       Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
       }
                         getAllPaniers() ;


    }
    @FXML
    public void loadEditpanier(MouseEvent e) throws IOException{

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            MenuController menuController = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("editPanier.fxml"));
            Parent root2 = loader2.load();
            if (listpanier.getSelectionModel().getSelectedItems().isEmpty()) {
                listPanierTopass = listPanier;
            } else {
                listPanierTopass = listpanier.getSelectionModel().getSelectedItems();
            }

            EditPanierController editPanierController = loader2.getController();
            editPanierController.passPaniertoEdit(listPanierTopass);
            BplistPanier.setCenter(root2);
            menucontroller.borderpanemain.setCenter(BplistPanier);
            
            

        } catch (RuntimeException err) {
            err.getMessage();
        }
    }
    
  //Delete a row from TableView 
    public void deleteRowTabView(ActionEvent e) {
        int SelectedId = listpanier.getSelectionModel().getSelectedIndex();
        listpanier.getItems().remove(SelectedId);
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
