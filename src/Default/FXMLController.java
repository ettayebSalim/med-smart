/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;


import Models.Fichier;
import Services.FichierService;
import Services.UserService;
import java.io.IOException;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
<<<<<<< HEAD
 * @author AGuizani
=======
 * @author NAD
>>>>>>> produit
 */
public class FXMLController implements Initializable {

    @FXML

    private Button Cancel;
    @FXML
    private Button ok;
    @FXML
    private Label type_fichier;
    @FXML
    private Label path;
    @FXML
    private Label user;
    @FXML
    private Label scenetitle;
    @FXML
    private TextField path_text;
    @FXML
    private TextField userId_text;
    @FXML
    private SplitMenuButton typeEnum;
    @FXML
    private MenuItem radio;
    @FXML
    private MenuItem scanner;
    @FXML
    private MenuItem irm;
    @FXML
    private MenuItem echo;
    @FXML
    private MenuItem analyse;
    @FXML
    private MenuItem ordonnance;
    @FXML
    private MenuItem lettre;
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML

     private void switchToscene2(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML_1.fxml"));
        stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addFile(ActionEvent e) throws IOException{
    String s;
     String idF;
     int id=0;
     s=typeEnum.getText();
     idF=path_text.getText();
     try{
     id=Integer.valueOf(userId_text.getText());
     }catch(NumberFormatException ex){
         ex.getMessage();
     }
         System.out.println(s);
         System.out.println(idF);
         System.out.println(id);
         UserService us = new UserService();
         FichierService f=new FichierService();
         Fichier fichier=new Fichier(s,idF,us.getUserByID(id));
         f.insertFichier(fichier);
         
    }

   
    private void action(ActionEvent event) {
    }
    

}
