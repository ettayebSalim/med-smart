/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Fichier;
import Services.FichierService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author AGuizani
 */
public class EditFileController implements Initializable {

    @FXML
    private AnchorPane ap_centereditfile;

    @FXML
    private AnchorPane apeditfile;

    @FXML
    private Button canceledit;

    @FXML
    public TableColumn<Fichier, Long> colfileid1;

    @FXML
    public TableColumn<Fichier, String> colfilename1;

    @FXML
    public TableColumn<Fichier, String> colfiletype1;

    @FXML
    public TableColumn<Fichier, Integer> coluserid1;

    @FXML
    public TableView<Fichier> editfile;

    @FXML
    private Button validateedit;

    private final ObservableList<Fichier> editFiles = FXCollections.observableArrayList();

    private ObservableList<Fichier> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editfile.refresh();
    }

    public void editAllFiles() {

        FichierService fs = new FichierService();
        List<Fichier> allFichier = fs.fetchFichiers();

        for (int i = 0; i < allFichier.size(); i++) {
            editFiles.add(allFichier.get(i));

        }
        colfileid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfiletype1.setCellValueFactory(new PropertyValueFactory<>("type"));
        colfilename1.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coluserid1.setCellValueFactory(cellData -> new SimpleIntegerProperty((cellData.getValue().getUser().getId())).asObject());
        editfile.setItems(editFiles);
    }

   public void passfiletoEdit(ObservableList<Fichier> x) throws IOException {
      
        editFiles.addAll(x); 
        
        System.out.println(editFiles); 
        
        colfileid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfiletype1.setCellValueFactory(new PropertyValueFactory<>("type"));
        colfilename1.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coluserid1.setCellValueFactory(cellData -> new SimpleIntegerProperty((cellData.getValue().getUser().getId())).asObject());
        editfile.setItems(editFiles);
    }


}
