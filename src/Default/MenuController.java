/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Fichier;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    private Button btu111;
    @FXML
    private Button btu411;
    @FXML
    private AnchorPane apMainMenu;
    @FXML
    private Button ListMedicament;

    
    
    
    
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
            
        }
        );

        //Setting the Translation of the Slider Menu
        
        slider.setTranslateX(-225);

        Menu.setOnMouseClicked(enent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-225);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(enent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.6));
            slide.setNode(slider);
            slide.setToX(-225);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);

            });
        });

        // Create Bloom Effect for the Slider menu buttons
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
        btl5.setOnMousePressed(event -> btl5.setEffect(bloom));
        btl5.setOnMouseReleased(event -> btl5.setEffect(bloom2));
        btl6.setOnMousePressed(event -> btl6.setEffect(bloom));
        btl6.setOnMouseReleased(event -> btl6.setEffect(bloom2));

        //Initialise Upper Menu
        FichierUpMenu.setVisible(false);
        MedicamentUpMenu.setVisible(false);
        EtablissementUpMenu.setVisible(false);
        PanierUpMenu.setVisible(false);
        RendezVousUpMenu.setVisible(false);
        UserUpMenu.setVisible(true);
        
        //setting up the  Slider Menu with the "OnMouseClicked" event
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
            loadUIMain("addFile");//on Mouse clicked first FXML addFile is loaded 

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
          
          
         ListMedicament.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            borderpanebutton.setCenter(MedicamentUpMenu);
            MedicamentUpMenu.setVisible(true);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(false);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
            loadUIMain("ListeProduit");
            
        });  
         PanierUpMenu.setOnMouseClicked(e -> {
            FichierUpMenu.setVisible(false);
            borderpanebutton.setCenter(MedicamentUpMenu);
            MedicamentUpMenu.setVisible(false);
            EtablissementUpMenu.setVisible(false);
            PanierUpMenu.setVisible(true);
            RendezVousUpMenu.setVisible(false);
            UserUpMenu.setVisible(false);
            loadUIMain("Panier");
            
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

   /*method that takes the FXML file name as input  which will be concatinated with ".fxml" 
    then  loaded and centerd in the "work area" the border pane borderpanemain */ 
    
    public void loadUIMain(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpanemain.setCenter(root);

    }



    private void userMenu(MouseEvent e) {
        loadUIMain("UI1");

    }


    private void medicamentMenu(MouseEvent e) {


        loadUIMain("UI1");

    }

    private void etablissementMenu(MouseEvent e) {

        loadUIMain("UI1");

    }

    private void rendezVousMenu(MouseEvent e) {

        loadUIMain("UI1");

    }
    
    
/* this method calls the loadUIMain method to add the addFile.fxml 
to the work area and associated to slider menu button btl5 "Fichier" and 
 the Upper menu button "AddFile"
Slider Menu:the button action is initialized in the initialize method
Upper Menu:the button action (Mouse click event) is set with Scene builder in the MainMenu.fxml*/
    @FXML
    private void fichierMenuAdd(MouseEvent e) {
        loadUIMain("addFile");
       
     
    }
 @FXML
    private void Panier(MouseEvent e) {
        loadUIMain("Panier");
       
     
    }
    /* this method calls the loadUIMain method to load the listFile.fxml 
to the work area and associated to Upper menu button "ListFile"
the button action (Mouse click event) is set with Scene builder in the MainMenu.fxml*/
    @FXML
    private void fichierMenulist(MouseEvent e) {
        loadUIMain("listFile");
        
    }

     /* this method calls the loadUIMain method to load the editFile.fxml 
to the work area and associated to Upper menu button "EditFile"
the button action (Mouse click event) is set with Scene builder in the MainMenu.fxml*/
    @FXML
    public void fichierMenuEdit(MouseEvent e) throws IOException {
        loadUIMain("editFile");


    }
@FXML
public void ajoutermedi(MouseEvent e) throws IOException {
        loadUIMain("ajouterProduit");


    }
@FXML
public void Suppmedi(MouseEvent e) throws IOException {
        loadUIMain("Suppproduit");


    }
public void ListMedicament(MouseEvent e) throws IOException {
    loadUIMain("ListeProduit");

    }
    private void panierMenu(MouseEvent e) {

        loadUIMain("Panier");

    }


    @FXML
    private void AddEtablissement(ActionEvent event) {

        
        
    }
    
    @FXML
    private void AddPanier(ActionEvent event) {

       loadUIMain("Panier"); 
        
    }

}
