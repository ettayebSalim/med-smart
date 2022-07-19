/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.IOException;
import javafx.animation.PauseTransition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author AGuizani
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            ImageView imageViewer = new ImageView("/icons/WordArtMain.png");
            imageViewer.setFitHeight(720);
            imageViewer.setFitWidth(1280);
            imageViewer.setPreserveRatio(true);
            imageViewer.setSmooth(true);
            imageViewer.setCache(true);
            imageViewer.autosize();
            Group root2 = new Group(imageViewer);
            Scene scene2 = new Scene(root2, imageViewer.getBoundsInParent().getWidth(), imageViewer.getBoundsInParent().getHeight());
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setResizable(false);
            stage2.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                stage2.close();
                Scene scene = new Scene(root, 1280, 800);
                String css = getClass().getResource("StyleCSS.css").toExternalForm();
                scene.getStylesheets().clear();

                scene.getStylesheets().add(css);

                Stage stage = new Stage();

                stage.setScene(scene);

                stage.setResizable(true);

                stage.show();

            });
            delay.play();

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
