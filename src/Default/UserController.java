package Default;

import Models.User;
import Services.UserService;
import Utiles.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class UserController implements Initializable{
    

    @FXML
    private TableColumn<User, Integer> colid;

    @FXML
    private Button btnView;

    @FXML
    private Button btnsupp;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<User, String> colcin;

    @FXML
    private TableColumn<User, String> colemail;

    @FXML
    private TableColumn<User,String> colrole;
    
    @FXML
    private TableColumn<User, String> colnom;

    @FXML
    private TableColumn<User, String> colprenom;

    @FXML
    private TableColumn<User, String> coltel;

    @FXML
    private TextField fxcin;

    @FXML
    private TextField fxemail;

    @FXML
    private TextField fxnom;

    @FXML
    private TextField fxprenom;

    @FXML
    private TextField fxrole;
    
    @FXML
    private TextField fxtel;

    @FXML
    private TableView<User> tableusers;
    
    @FXML
    private TextField fxid;
    
    @FXML
    private TextField fxsearch;
    
    ObservableList<User> listUsers;
    ObservableList<User> listUserFiltred;
    
    private User utilisateur;
    
    UserService userService = new UserService();
    
    

        PreparedStatement ps = null;



    @FXML
    void updateUser(ActionEvent event) {

    }
    
     @FXML
    void viewUser(ActionEvent event) {
    if(tableusers.getSelectionModel().isEmpty()){
           JOptionPane.showMessageDialog(null, "Veuillez selectionner un utilisateur pour voir son profil");

     }
        int selectedUser = tableusers.getSelectionModel().getSelectedIndex();
        
        
        fxid.setText(colid.getCellData(selectedUser).toString());
        fxid.setDisable(true);
        
        fxnom.setText(colnom.getCellData(selectedUser));
        
        fxprenom.setText(colprenom.getCellData(selectedUser));
        
        fxemail.setText(colemail.getCellData(selectedUser));
        
        fxcin.setText(colcin.getCellData(selectedUser));
        
        fxtel.setText(coltel.getCellData(selectedUser));

        fxrole.setText(colrole.getCellData(selectedUser));
        fxrole.setDisable(true);
        searchUser();

    }
    
     @FXML
    void deleteUser(ActionEvent event) {
       
        if(fxid.getText().isEmpty()){
           
            JOptionPane.showMessageDialog(null, "Veuillez selectionner et afficher le profil de l'utilisateur à supprimer ");


        }else{
    
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Voulez-vous supprimer "+fxnom.getText()+" "+fxprenom.getText()+" ?");

            ButtonType buttonTypeOne = new ButtonType("Oui");
            ButtonType buttonTypeCancel = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                 try{
                 System.out.println("Utilisateur à supprimer"+fxid.getText());
             String query = "delete from user where id ="+fxid.getText();
             
             Connection cnx2 = MyConnection.getInstance().getCnx();
             ps = cnx2.prepareStatement(query);
             ps.execute();
             JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succés");
            fxid.setText(null);
            fxnom.setText(null);
            fxprenom.setText(null);
            fxemail.setText(null);
            fxcin.setText(null);
            fxtel.setText(null);
            fxrole.setText(null);
            showUsers();
            
            }catch(SQLException e){
                e.printStackTrace();
            }
            }
            if(result.get() == buttonTypeCancel){
             fxid.setText(null);
            fxnom.setText(null);
            fxprenom.setText(null);
            fxemail.setText(null);
            fxcin.setText(null);
            fxtel.setText(null);
            fxrole.setText(null);
            showUsers();
            }
        }
      
        searchUser();

    }
    
      @FXML
    void searchUser() {
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
      colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
      coltel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
      
      listUserFiltred= userService.fetchUsers();
      tableusers.setItems(listUserFiltred);


      FilteredList<User> filteredData = new FilteredList<>(listUserFiltred, b ->true);
      fxsearch.textProperty().addListener((observable,oldValue,newValue) ->{
              filteredData.setPredicate(utilisateur ->{
                 if(newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                 
                 if(utilisateur.getNom().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                     return true;
                 }else if(utilisateur.getPrenom().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                     return true;
                 }else if(String.valueOf(utilisateur.getEmail()).toLowerCase().indexOf(lowerCaseFilter)!= -1){
                     return true;
                 }else if(utilisateur.getCin().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                     return true;
                 }else if(utilisateur.getNumtel().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                     return true;  
                 }else if(utilisateur.getRole().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                     return true;
                 }else
                  return false;
              });
      });
      
      SortedList<User> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(tableusers.comparatorProperty());
      tableusers.setItems(sortedData);
     
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsers();
        searchUser();
        
      
    }

    
    public void showUsers(){
        
      listUsers= userService.fetchUsers();
      System.out.println("Size of list of Users ==>"+listUsers.size());
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
      colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
      coltel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
      tableusers.setItems(listUsers);
    }


//    private void addUser(){
//             String query = "INSERT INTO `user` VALUES ("+null+",'"+fxnom.getText()+"','"+fxprenom.getText()+"','"+fxemail.getText()+"','"+fxcin.getText()+"','"+fxmdp.getText()+"','"+fxrole.getText()+"','"+fxtel.getText()+"')";
//             System.out.println(query);
//           executeQuery(query);
//            showUsers();
//    }

    private void executeQuery(String query){
        Connection cnx2 = MyConnection.getInstance().getCnx();
        Statement st;
        try{
            st = cnx2.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    
}
