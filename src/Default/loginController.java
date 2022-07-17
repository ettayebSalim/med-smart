package Default;

import Utiles.MyConnection;
import Utiles.SceneManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class loginController implements Initializable{

    @FXML
    private Button btnlog;

    @FXML
    private TextField fxmail;
    
    @FXML
    private PasswordField fxpwd;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    void login(ActionEvent event) {

        String mail = fxmail.getText();
        String pwd =  fxpwd.getText();
        
        
        if(mail.isEmpty() && pwd.isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez remplir vos coordonnées");
        }
        else if(mail.isEmpty() && !pwd.isEmpty()){
           JOptionPane.showMessageDialog(null, "Veuillez entrer votre adresse mail");

        }
        else if( !mail.isEmpty() && pwd.isEmpty()){
           JOptionPane.showMessageDialog(null, "Veuillez entrer votre mot de passe");
           
        }
        else{
            try{
                
            con = MyConnection.getInstance().getCnx();
            pst=con.prepareStatement("select * from user where email = ? and hashedpwd = ? ");
            
            pst.setString(1, mail);
            pst.setString(2, pwd);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

            Scene scene = new Scene(root, 925, 625);
            String css = getClass().getResource("StyleCSS.css").toExternalForm();
            scene.getStylesheets().clear();

            scene.getStylesheets().add(css);
            
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

        
            }else{
                JOptionPane.showMessageDialog(null, "Echec d'authentification! Les données sont invalides");
                fxmail.setText(null);
                fxpwd.setText(null);
                fxmail.requestFocus();
            }
            
            }catch(Exception ex){
            ex.printStackTrace();
        }

        }
    }

}
