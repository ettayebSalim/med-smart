/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author AGuizani
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

            Scene scene = new Scene(root, 1280, 800);
            String css = getClass().getResource("StyleCSS.css").toExternalForm();
            scene.getStylesheets().clear();

            scene.getStylesheets().add(css);

            Stage stage = new Stage();

            stage.setScene(scene);

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
