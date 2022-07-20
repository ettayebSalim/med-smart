/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Etablissments;
import Services.EtabCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ListeEtabController implements Initializable {

    @FXML
    private GridPane grid;
    private SupprimerEtab supEtab;
    private ModifierEtab modif;
            
    @FXML
    private AnchorPane MyPane;
    @FXML
    private TextField TxtNameModif;
    @FXML
    private TextField TxtTypeModif;
    @FXML
    private TextField TxtAdresseModif;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    

    private void modifier(Etablissments etab){

        
        TxtNameModif.setText(etab.getName());
        TxtTypeModif.setText(etab.getType());
        TxtAdresseModif.setText(etab.getAdresse());
        setId((int) etab.getId());
                
                
    }
private void supprimerr(Etablissments etab){
         EtabCRUD etabli =new EtabCRUD();
         etabli.deleteEtab((int) etab.getId());
         
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Etabissement deleted successfully");
            alert.showAndWait();
            
            
           MyPane.getChildren().clear();
            
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ListeEtab.fxml"));
                 
            AnchorPane anchorPane = cards.load();

            MyPane.getChildren().add(anchorPane);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EtabCRUD etab =new EtabCRUD();
        
        
         if(etab.afficherEtab().size()>0){
         supEtab=new SupprimerEtab() {
             @Override
             public void supprimer(Etablissments etab) {
                 supprimerr(etab);
             }
         };
         modif=new ModifierEtab() {
             @Override
             public void ModifierEtabl(Etablissments etab) {
                 modifier(etab);
             }
         };
             
         }
        
        int column =1;
        int row=0;
       
       try{
        for(int i=0;i<etab.afficherEtab().size();i++){
            
            FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("Etab.fxml"));

                AnchorPane anchorPane = cards.load();

                EtabController Etabservice = cards.getController();

                Etabservice.setData(etab.afficherEtab().get(i),supEtab,modif);
               

                // System.out.println("hhh");
                // System.out.println(offreservice.getAllHoreca().get(i).getClass().getSimpleName());
                if (column == 2) {
                    column = 1;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));
            }

////                  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            
        }

    @FXML
    private void ModiferEtablissement(ActionEvent event) {
        
        EtabCRUD service=new EtabCRUD();
        
        
        Etablissments e =new Etablissments(TxtNameModif.getText(), TxtTypeModif.getText(), TxtAdresseModif.getText());
        System.out.println(id);
        service.updateEtab(e, getId());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Etabissement Modified successfully");
            alert.showAndWait();
            
            
           MyPane.getChildren().clear();
            
             try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ListeEtab.fxml"));
                 
            AnchorPane anchorPane = cards.load();

            MyPane.getChildren().add(anchorPane);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }
                
                
               
    }    
    

