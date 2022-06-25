/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Fichier;
import Services.FichierService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author AGuizani
 */
public class ListFileController implements Initializable {

    @FXML
    private BorderPane BplistFile;

    @FXML
    private AnchorPane ap_centerlistfile;

    @FXML
    private AnchorPane aplistfile;

    @FXML
    private TableColumn<Fichier, Long> colfileid;

    @FXML
    private TableColumn<Fichier, String> colfilename;

    @FXML
    private TableColumn<Fichier, String> colfiletype;

    @FXML
    private TableColumn<Fichier, Integer> coluserid;

    @FXML
    public TableView<Fichier> listfile;

    @FXML
    public Button editmode;

    @FXML
    private Button down;

    @FXML
    private Button up;

    @FXML
    private TextField filebyid;

    @FXML
    private TextField filebyname;

    @FXML
    public BorderPane borderpanemain;

    private MenuController menucontroller;

    public ObservableList<Fichier> listFile = FXCollections.observableArrayList();
    public ObservableList<Fichier> listFileTopass = FXCollections.observableArrayList();
    private int n = 0;
    final int m = 25;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fetchNFiles(0, 25);
        listfile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

//Filter By Idphysique text field  
        FichierService fs = new FichierService();
        filebyname.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                getFileByIdPhysique();
            }
        });

        filebyid.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                getFileById();
            }
        });

    }

    public void showTableView() {
        listfile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colfileid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfiletype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colfilename.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coluserid.setCellValueFactory(cellData -> new SimpleIntegerProperty((cellData.getValue().getUser().getId())).asObject());
        listfile.setItems(listFile);
    }

    @FXML
    public void fetchFiles() {
        n = 0;
        listfile.getItems().removeAll(listFile);
        FichierService fs = new FichierService();
        List<Fichier> allFichier = fs.fetchFichiers();

        for (int i = 0; i < allFichier.size(); i++) {
            listFile.add(allFichier.get(i));

        }
        showTableView();

    }

    @FXML
    public int fetchNFiles(int n, int m) {
        FichierService fs = new FichierService();
        List<Fichier> allFichier = fs.fetchNFichiers(n, m);

        for (int i = 0; i < allFichier.size(); i++) {
            listFile.add(allFichier.get(i));

        }
        showTableView();
        return n;
    }

    @FXML
    public void moveListUp() {
        FichierService fs = new FichierService();
        n = n + m;

        listfile.getItems().removeAll(listFile);
        int max = fs.numberOfRows();
        if (n <= (max / m) * m) {
            List<Fichier> allFichier = fs.fetchNFichiers(n, m);
            for (int i = 0; i < allFichier.size(); i++) {
                listFile.add(allFichier.get(i));

            }
        } else {
            n = (max / m) * m;
            List<Fichier> allFichier = fs.fetchNFichiers((max / m) * m, max);;
            for (int i = 0; i < allFichier.size(); i++) {
                listFile.add(allFichier.get(i));
            }
        }
        showTableView();

    }

    @FXML
    public void moveListdowwn() {
        FichierService fs = new FichierService();
        n = n - m;

        listfile.getItems().removeAll(listFile);
        if (n > 0) {

            List<Fichier> fichier = fs.fetchNFichiers(n, m);
            for (int i = 0; i < fichier.size(); i++) {
                listFile.add(fichier.get(i));

            }
            listfile.setItems(listFile);
        } else {
            n = 0;
            fetchNFiles(0, 25);

        }

    }

    public void getFileById() {
        FichierService fs = new FichierService();

        try {
            int id = Integer.parseInt(filebyid.getText());
            try {
                Fichier fichier = fs.getFichierById(id);
                System.out.println(fichier);
                listfile.getItems().removeAll(listFile);
                listFile.add(fichier);
                showTableView();
            } catch (NullPointerException npe) {
                listfile.getItems().removeAll(listFile);
                listfile.setPlaceholder(new Label("File Not Found"));
            }
        } catch (NumberFormatException ex) {
            listfile.getItems().removeAll(listFile);
            listfile.setPlaceholder(new Label("Please enter only valid Integer "));
        }

    }

    public void getFileByIdPhysique() {
        FichierService fs = new FichierService();
        if (!filebyname.getText().equals("")) {
            listfile.getItems().removeAll(listFile);
            String s = filebyname.getText();
            try {
                Fichier fichier = fs.getFichierByIdPhysique(s);
                System.out.println(fichier);
                listFile.add(fichier);
                showTableView();
            } catch (NullPointerException ex) {
                listfile.getItems().removeAll(listFile);
                listfile.setPlaceholder(new Label("File Not Found!"));
            }
        } else {

            listfile.getItems().removeAll(listFile);
            listfile.setPlaceholder(new Label("Please Enter a Valid File Name"));
        }
    }

    @FXML
    public void loadEditfile(MouseEvent e) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            MenuController menuController = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("editFile.fxml"));
            Parent root2 = loader2.load();
            if (listfile.getSelectionModel().getSelectedItems().isEmpty()) {
                listFileTopass = listFile;
            } else {
                listFileTopass = listfile.getSelectionModel().getSelectedItems();
            }

            EditFileController editFileController = loader2.getController();
            editFileController.passfiletoEdit(listFileTopass);
            BplistFile.setCenter(root2);
            menucontroller.borderpanemain.setCenter(BplistFile);

        } catch (RuntimeException err) {
            err.getMessage();
        }
    }

    public void SearchTableByTypeOrUser() {

    }
    
}
