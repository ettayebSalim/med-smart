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
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 *
 * @author AGuizani
 */
public class EditFileController implements Initializable {


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
    private TextField fileNameText;
       
    @FXML
    private ComboBox<String> fileTypeChoice;

    private final ObservableList<Fichier> editFiles = FXCollections.observableArrayList();

    private ObservableList<Fichier> observableList = FXCollections.observableArrayList();

    private Path fileAbsPath;
    
    private String[] choice = {"RADIO", "SCANNER", "IRM", "ECHO", "ANALYSE_LABO", "ORDONNANCE", "LETTRE_DE_LIAISON"};
    
    private final String fileout = "C:\\Users\\AGuizani\\Desktop\\med-smart _CodeNameOne\\backNodeCodeNameOne\\backNodeCodeNameOne\\public\\fichiers\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editfile.refresh();
         //setting-up choiceboxx
        
        fileTypeChoice.getItems().addAll(choice);
        fileTypeChoice.setOnAction(this::getTypeFichierChoice);

    }

    public void showTableView() {
        editfile.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colfileid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfiletype1.setCellValueFactory(new PropertyValueFactory<>("type"));
        colfilename1.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coluserid1.setCellValueFactory(cellData -> new SimpleIntegerProperty((cellData.getValue().getUser().getId())).asObject());
        editfile.setItems(editFiles);
    }

    @FXML
    public void editAllFiles() {

        FichierService fs = new FichierService();
        List<Fichier> allFichier = fs.fetchFichiers();

        for (int i = 0; i < allFichier.size(); i++) {
            editFiles.add(allFichier.get(i));

        }
        showTableView();
    }

    public void passfiletoEdit(ObservableList<Fichier> x) throws IOException {

        editFiles.addAll(x);

        System.out.println(editFiles);

        showTableView();
    }

    //select a File from Local Drive
    @FXML
    public void selectFile(ActionEvent e) throws IOException {
        FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            fileNameText.setText(selectedFile.getName());
            fileAbsPath = selectedFile.getAbsoluteFile().toPath();
        } else {
            fileNameText.setText("file Not Valid");
        }

    }

     @FXML
    public String getTypeFichierChoice(ActionEvent e) {

        String s = fileTypeChoice.getValue();
        
        return s;
    }


    
    
    
    
    
    
    
    
    
}
