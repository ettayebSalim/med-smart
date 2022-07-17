package Default;

import Models.User;
import Services.UserService;
import Utiles.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController implements Initializable{
    

    @FXML
    private TableColumn<User, Integer> colid;

    @FXML
    private Button btnajout;

    @FXML
    private Button btnsupp;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<User, String> colcin;

    @FXML
    private TableColumn<User, String> colemail;

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
    private TextField fxmdp;

    @FXML
    private TextField fxprenom;

    @FXML
    private TextField fxrole;


        
    @FXML
    private TextField fxtel;

    @FXML
    private TableView<User> tableusers;
    
    private User utilisateur;
    
    UserService userService = new UserService();
    
    

        PreparedStatement ps = null;



    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {

        if(event.getSource() == btnajout){
            addUser();
            
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsers();
        
      
    }

    
    public void showUsers(){
        
     ObservableList<User> listUsers= userService.fetchUsers();
      System.out.println("Size of list of Users ==>"+listUsers.size());
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
      colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
      coltel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      tableusers.setItems(listUsers);
    }


    private void addUser(){
             String query = "INSERT INTO `user` VALUES ("+null+",'"+fxnom.getText()+"','"+fxprenom.getText()+"','"+fxemail.getText()+"','"+fxcin.getText()+"','"+fxmdp.getText()+"','"+fxrole.getText()+"','"+fxtel.getText()+"')";
             System.out.println(query);
           executeQuery(query);
            showUsers();
    }

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
