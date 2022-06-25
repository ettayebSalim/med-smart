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
import javafx.scene.Node;
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
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author AGuizani
 */
public class MenuController implements Initializable {

    @FXML
    private HBox FichierUpMenu;

    @FXML
    private HBox MedicamentUpMenu;

    @FXML
    private HBox EtablissementUpMenu;

    @FXML
    private HBox PanierUpMenu;

    @FXML
    private HBox RendezVousUpMenu;

    @FXML
    private HBox UserUpMenu;


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
    public BorderPane borderpanemain;

    @FXML
    private Button btl1;

    @FXML
    private Button btl2;

    @FXML
    private Button btl3;

    @FXML
    private Button btl4;

    @FXML
    private Button btl5;

    @FXML
    private Button btl6;

    @FXML
    private Pane p_up;

    @FXML
    private AnchorPane slider;
    @FXML
    private Button btuuser1;
    @FXML
    private Button btuuser2;
    @FXML
    private Button btuuser3;
    @FXML
    private Button btuuser4;
    @FXML
    private Button btuuser5;
    @FXML
    private AnchorPane submenuAnchorpane;
    @FXML
    private Button btumed1;
    @FXML
    private Button btumed2;
    @FXML
    private Button btumed3;
    @FXML
    private Button btumed4;
    @FXML
    private Button btumed5;
    @FXML
    private Button btu111;
    @FXML
    private Button btu211;
    @FXML
    private Button btu411;
    @FXML
    private Button btu511;
    @FXML
    private Button btu1111;
    @FXML
    private Button btu2111;
    @FXML
    private Button btu3111;
    @FXML
    private Button btu4111;
    @FXML
    private Button btu5111;
    @FXML
    private Button btu1112;
    @FXML
    private Button btu2112;
    @FXML
    private Button btu3112;
    @FXML
    private Button btu4112;
    @FXML
    private Button btu5112;
    @FXML
    private Button btu6112;
    @FXML
    private Button btu11121;
    @FXML
    private Button btu21121;
    @FXML
    private Button btu31121;
    @FXML
    private Button btu41121;
    @FXML
    private Button btu51121;

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

        
        //Initialise Upper Menu
        FichierUpMenu.setVisible(false);
        MedicamentUpMenu.setVisible(false);
        EtablissementUpMenu.setVisible(false);
        PanierUpMenu.setVisible(false);
        RendezVousUpMenu.setVisible(false);
        UserUpMenu.setVisible(true);

        //Change Upper Menu
        btl1.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(true);
            borderpanebutton.setCenter(UserUpMenu);
        });
        btl2.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            MedicamentUpMenu.setVisible(true);
            borderpanebutton.setCenter(MedicamentUpMenu);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
        });

        btl3.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(true);
            borderpanebutton.setCenter(EtablissementUpMenu);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
        });

        btl4.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(true);
            borderpanebutton.setCenter(RendezVousUpMenu);
            UserUpMenu.setVisible(false);
        });

        btl5.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(true);
            borderpanebutton.setCenter(FichierUpMenu);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
             loadUIMain("addFile");
            
        });
        btu111.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            borderpanebutton.setCenter(EtablissementUpMenu);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(true);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
             loadUIMain("AddEtablissement");
            
        });
          btu411.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            borderpanebutton.setCenter(EtablissementUpMenu);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(true);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
            loadUIMain("ListeEtab");
            
        });
        btl6.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(true);
            borderpanebutton.setCenter(PanierUpMenu);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
        });
    }

    public void loadUIMain(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpanemain.setCenter(root);

    }

    public void loadUIButton(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpanebutton.setCenter(root);

    }

    private void userMenu(MouseEvent e) {
        loadUIMain("addFile");

    }

    public void medicamentMenu(MouseEvent e) {

        loadUIMain("UI1");

    }

    private void etablissementMenu(MouseEvent e) {

        loadUIMain("UI1");

    }

    private void rendezVousMenu(MouseEvent e) {

        loadUIMain("UI1");

    }

    @FXML
    private void fichierMenuAdd(MouseEvent e) {
        loadUIMain("addFile");
    }

    @FXML
    private void fichierMenuEdit(MouseEvent e) {
        loadUIMain("UI1");
    }

    private void panierMenu(MouseEvent e) {

        loadUIMain("UI1");

    }

    @FXML
    private void AddEtablissement(ActionEvent event) {

        
        
    }
}
