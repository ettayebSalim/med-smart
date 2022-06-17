/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import com.sun.scenario.effect.Glow;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author AGuizani
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane ap;

    @FXML
    private AnchorPane ap_up;

    @FXML
    private BorderPane borderpanebutton;

    @FXML
    private BorderPane borderpanemain;

    @FXML
    private Button btl1;

    @FXML
    private Button btl2;

    @FXML
    private Button btl3;

    @FXML
    private Button btl4;

    @FXML
    private Pane p_up;

    @FXML
    private AnchorPane slider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Exit.setOnMouseClicked(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Quit SmartCare");
            alert.setHeaderText("Quit SmartCare? ");

            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                System.exit(0);
            }
            //System.exit(0);
        }
        );

        slider.setTranslateX(-176);

        Menu.setOnMouseClicked(enent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.1));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(enent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.6));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);

            });
        });
        

        
        // Create Bloom Effect
        Bloom bloom = new Bloom();
        Bloom bloom2 = new Bloom();
        // Setting Threshold
        bloom.setThreshold(0.8);
        bloom2.setThreshold(1);
        btl1.setOnMousePressed(event -> btl1.setEffect(bloom));
        btl1.setOnMouseReleased(event -> btl1.setEffect(bloom2));
        btl2.setOnMousePressed(event -> btl2.setEffect(bloom));
        btl2.setOnMouseReleased(event -> btl2.setEffect(bloom2));
        btl3.setOnMousePressed(event -> btl3.setEffect(bloom));
        btl3.setOnMouseReleased(event -> btl3.setEffect(bloom2));
        btl4.setOnMousePressed(event -> btl4.setEffect(bloom));
        btl4.setOnMouseReleased(event -> btl4.setEffect(bloom2));
        

    }
    

    private void loadUIMain(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpanemain.setCenter(root);
    }

    private void loadUIButton(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpanebutton.setCenter(root);
    }

    @FXML
    private void ui1(MouseEvent e) {

        loadUIMain("addFile");
        loadUIButton("UI1_Button");

    }

    @FXML
    private void ui2(MouseEvent e) {

        loadUIMain("UI1");

    }

    @FXML
    private void ui3(MouseEvent e) {

        loadUIMain("UI1");

    }

}
