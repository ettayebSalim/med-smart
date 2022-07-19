/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bureau
 */
public class SceneManager {
    	public static void changeScene(ActionEvent event, String fxmlFile, String title) {

		Parent root = null;
		fxmlFile = "/Default/" + fxmlFile;
	
			try {
				root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
 
            
			} catch (IOException e) {
				e.printStackTrace();
			}
		

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setTitle(title);
		stage.setScene(new Scene(root, 1000, 700));

		stage.show();
	}

	public static void changeSceneForMenuBar(Stage menubar, String fxmlFile, String title) {

		Parent root = null;
		fxmlFile = "/Default/" + fxmlFile;
		try {
			root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Stage stage = menubar;
		stage.setTitle(title);
		stage.setScene(new Scene(root, 1000, 700));
		stage.show();
	}
}
