package Default;


import Utiles.MyConnection;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class RegisterController implements Initializable {

    @FXML
    private Button btnajout;

    @FXML
    private Button btnannuler;

    @FXML
    private TextField fxcin;

    @FXML
    private TextField fxemail;

    @FXML
    private TextField fxnom;

    @FXML
    private TextField fxprenom;

    @FXML
    private ComboBox<String> fxrole;

    @FXML
    private TextField fxtel;
    

    
    @FXML
    void goToLoginPage(MouseEvent event) {

            try{
           Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));

            Scene scene = new Scene(root, 925, 625);
            String css = getClass().getResource("StyleCSS.css").toExternalForm();
            scene.getStylesheets().clear();

            scene.getStylesheets().add(css);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void addUser(ActionEvent event) {
           String query = "INSERT INTO `user` VALUES ("+null+",'"+fxnom.getText()+"','"+fxprenom.getText()+"','"+fxemail.getText()+"','"+fxcin.getText()+"','"+fxcin.getText()+"','"+fxrole.getSelectionModel().getSelectedItem()+"','"+fxtel.getText()+"')";
           System.out.println(query);
           executeQuery(query);
         
           Alert succesAlert = new Alert(AlertType.INFORMATION);
           succesAlert.setHeaderText("Bienvenue");
           succesAlert.setContentText("Mr,Mme "+fxnom.getText()+" "+fxprenom.getText()+" ,le nouveau "+fxrole.getValue()+" est ajouté avec succés");
           succesAlert.showAndWait();
           
      Twilio.init("ACf036909e6670a84aedcd7165f1ab337d", "ce721abffd6efbf6baf9b36d7f9be0a3");
        Message message = Message.creator(new PhoneNumber("+216"+fxtel.getText()),
                new PhoneNumber("+18623566984"),
                "Bienvenue sur notre application, Pour se connecter voici votre login "+fxemail.getText()+" et votre mot de passe est : "+fxcin.getText()).create();
        System.out.println(message.getSid());
            
  
           fxnom.setText(null);
           fxprenom.setText(null);
           fxemail.setText(null);
           fxcin.setText(null);
           fxtel.setText(null);
           fxrole.setValue(null);

  
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


    @FXML
    void reset(ActionEvent event) {

        fxnom.setText(null);
        fxprenom.setText(null);
        fxemail.setText(null);
        fxcin.setText(null);
        fxtel.setText(null);
        fxrole.setValue(null);
  
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       ObservableList<String> listRole
           = FXCollections.observableArrayList("Admin", "Médecin", "Patient","Technicien");
       
       fxrole.setItems(listRole);
    }
    

    

    

}
