/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author AGuizani
 */
public class SceneController  {

    private Stage stage;
    private Scene scene;
    private Parent root;
     @FXML
    private Button add;
    @FXML
    private Button view;
    
    @FXML
    private Button delete;
    
    
@FXML
    private void switchToscene1(ActionEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FichierScene2.fxml"));
        stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
