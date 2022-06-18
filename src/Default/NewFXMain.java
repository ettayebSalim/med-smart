/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

/**
 *
<<<<<<< HEAD
 * @author AGuizani
=======
 * @author NAD
>>>>>>> produit
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML_1.fxml"));
            
            Scene scene = new Scene(root, 600, 500);
           
            scene.setFill(Color.LIGHTCYAN);
         
            Stage stage = new Stage();
            stage.setTitle("MedSmart");
            stage.setScene(scene);
            Image image = new Image("care.png");
            stage.getIcons().add(image);
            stage.setResizable(true);
            
            stage.show();
            
        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
