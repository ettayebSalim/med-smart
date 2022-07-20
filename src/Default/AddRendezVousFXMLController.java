/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Etablissments;
import Models.User;
import Services.UserService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author e.bentijani
 */
public class AddRendezVousFXMLController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private Label lbAffectedDate;
    @FXML
    private ChoiceBox<?> heure;
    @FXML
    private ChoiceBox<String> idMed;
    @FXML
    private ChoiceBox<String> idEtab;
   UserService us =  new UserService();
   //UserService us = UserService.GetInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<User> listUser=new ArrayList<User>();
         List<Etablissments> listEtab=new ArrayList<Etablissments>();

            listUser= us.fetchUsers();
            for (User user : listUser) {
                if(user.getRole().equals("Medecin"))
            idMed.getItems().add(user.getPrenom()+" "+ user.getNom());
        }

    }     

    @FXML
    private void affecter(ActionEvent event) {
    }
    
}
