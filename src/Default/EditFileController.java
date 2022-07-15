/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Fichier;
import Models.User;
import Services.FichierService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

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
    public TableColumn<Fichier, String> coluserid1;

    @FXML
    public TableView<Fichier> editfile;

    @FXML
    private TextField fileNameText;

    @FXML
    private TextField fileIDText;

    @FXML
    private TextField fileTypeText;

    @FXML
    private ComboBox<String> fileTypeChoice;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private final ObservableList<Fichier> editFiles = FXCollections.observableArrayList();
    private String[] choice = {"RADIO", "SCANNER", "IRM", "ECHO", "ANALYSE_LABO", "ORDONNANCE", "LETTRE_DE_LIAISON"};
    private final String fileout = "C:\\Users\\AGuizani\\Desktop\\med-smart _CodeNameOne\\backNodeCodeNameOne\\backNodeCodeNameOne\\public\\fichiers\\";
    private static String extension = null;
    private static String fileName = null;
    private static int selectedidx = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileIDText.setEditable(false);
        fileTypeText.setEditable(false);
        editfile.refresh();
        editfile.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //setting-up choicebox
        fileTypeChoice.getItems().addAll(choice);
        fileTypeChoice.setOnAction(this::getTypeFichierChoice);

        editfile.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                fileIDText.setText(Long.toString(newSelection.getId()));

                String s = newSelection.getIdPhysique();
                extension = s.substring(s.lastIndexOf("."));
                fileName = s.substring(0, s.lastIndexOf("."));
                fileNameText.setText(fileName);

                fileTypeText.setText(newSelection.getType());
                selectedidx = editfile.getSelectionModel().getSelectedIndex();
                

                updateButton.setOnMouseClicked(e -> {
                    if (!fileNameText.getText().isEmpty()) {

                        System.out.println("fileName before change " + fileName);
                        boolean result = updateFichierlocal(fileName, extension);
                        fileName = fileNameText.getText();
                        System.out.println("fileName after change " + fileName);
                        if (result) {
                            try {
                                String fname = fileNameText.getText().trim() + extension;
                                User user = editFiles.get(selectedidx).getUser();
                                Fichier f = new Fichier(newSelection.getId(), fileTypeText.getText(), fname, user);
                                System.out.println(f);
                                boolean result2 = updateFichierDB(f, Long.parseLong(fileIDText.getText()));
                                if (result2) {
                                    editFiles.set(selectedidx, f);
                                    showTableView();
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(EditFileController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        System.out.println("Please Update File  Fields");
                    }
                });

                deleteButton.setOnMouseClicked(e -> {
                    fileNameText.setText(fileName);
                    fileTypeText.setText(newSelection.getType());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete a File");
                    alert.setHeaderText("Are you sour you want to delete thr file " + fileName + " ?");
                    ButtonType buttonTypeOne = new ButtonType("Yes");
                    ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {

                        System.out.println("fileName before change " + fileName);
                        boolean result3 = deleteFichierlocal(fileName, extension);

                        if (result3) {
                            boolean result4 = deleteFichierDB(Long.parseLong(fileIDText.getText()));
                            if (result4) {
                                editFiles.remove(selectedidx);
                                fileTypeText.setText("");
                                fileIDText.setText("");
                                fileNameText.setText("");
                                showTableView();
                            }

                        }
                    }
                });

            }
        });

    }

    public void showTableView() {
        editfile.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colfileid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfiletype1.setCellValueFactory(new PropertyValueFactory<>("type"));
        colfilename1.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coluserid1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getCin()));
        //coluserid1.setCellValueFactory(cellData -> new SimpleIntegerProperty((cellData.getValue().getUser().getId())).asObject());
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

    public String getTypeFichierChoice(ActionEvent e) {

        String s = fileTypeChoice.getValue();
        fileTypeText.setText(s);
        return s;
    }

    public boolean updateFichierDB(Fichier f, long numb) {
        FichierService fs = new FichierService();
        boolean result = false;
        if (!fileIDText.getText().equals("")) {
            try {
                fs.updateFichierById(f, numb);
                result = true;
                System.out.println("updateFichierDB succsess");
            } catch (NumberFormatException ex) {
                System.out.println("Please Enter A Valid Fichier Id!");
            }
        }
        return result;
    }

    public boolean deleteFichierDB(long id) {
        boolean result = false;
        FichierService fs = new FichierService();
        if (!fileIDText.getText().equals("")) {
            try {
                fs.DeleteFichierById(id);
                result = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please Enter A Valid Fichier Id!");

            }
        }
        return result;
    }

    public boolean updateFichierlocal(String fname, String ext) {

        boolean result = false;
        File source = new File(fileout + fname.trim() + ext);
        System.err.println("source: " + fileout + fname + ext);

        File target = new File(fileout + fileNameText.getText().trim() + ext);
        System.err.println("target: " + fileout + fileNameText.getText() + ext);
        if (!source.getName().equals(target.getName())) {
            if (!target.exists()) {
                try {

                    FileUtils.moveFile(source, target);
                    result = true;
                    System.out.println("updateFichierlocal succsess");
                } catch (IOException e) {
                    e.printStackTrace();
                    result = false;
                }
            } else {
                System.out.println("Target File Already exists");
                result = false;
            }
        } else {
            System.out.println("Target equals Source");
            result = true;
        }
        return result;
    }

    public boolean deleteFichierlocal(String fname, String ext) {

        Path target = Paths.get(fileout + fname + ext);

        boolean result;
        try {
            result = Files.deleteIfExists(target);

            if (result) {
                System.out.println("File is deleted!");
            } else {
                System.out.println(" unable to delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;

    }

}
