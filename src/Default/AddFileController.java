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
import java.net.URL;
import java.util.List;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import static Services.TypeFichierService.checkType;
import Services.UserService;
import java.io.IOException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author AGuizani
 */
public class AddFileController implements Initializable {

    @FXML
    private Label msg;

    @FXML
    private TableView<Fichier> addTable;

    @FXML
    private AnchorPane apaddfile;

    @FXML
    private AnchorPane ap_center;

    @FXML
    private Button canceladd;

    @FXML
    private TableColumn<Fichier, String> colfilename;

    @FXML
    private TableColumn<Fichier, String> coltype;

    @FXML
    private Button delrow;

    @FXML
    private Button selectbutton;

    @FXML
    private Button setalltype;

    @FXML
    private Button clear;

    @FXML
    private TextField userid;

    @FXML
    private Button validateadd;

    @FXML
    private CheckBox checkchoice;

    @FXML
    private ChoiceBox<String> typechoice;

    private ObservableList<Fichier> list = FXCollections.observableArrayList();
    private ObservableList<Fichier> list2 = FXCollections.observableArrayList();
    private String[] choice = {"RADIO", "SCANNER", "IRM", "ECHO", "ANALYSE_LABO", "ORDONNANCE", "LETTRE_DE_LIAISON"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // setting the File_Type column editable
        addTable.setEditable(true);
        coltype.setCellFactory(TextFieldTableCell.forTableColumn());
        //setting-up choiceboxx
        typechoice.setDisable(true);
        typechoice.getItems().addAll(choice);
        typechoice.setOnAction(this::getTypeFichieChoice);

    }

    
    //select Files from Local
    @FXML
    public void selectMultipleFile(ActionEvent e) throws IOException {
        FileChooser fc = new FileChooser();

        List<File> selectedFile = fc.showOpenMultipleDialog(null);
        boolean bool = this.activateChoiceBox(e);
        if (selectedFile != null) {
            for (int i = 0; i < selectedFile.size(); i++) {

                String s = selectedFile.get(i).getPath();

                if (!bool) {
                    list.add(new Fichier(this.getTypeFichieChoice(e), s));
                } else {
                    list.add(new Fichier("radio", s));
                }

            }

        } else {
            msg.setText("file Not Valid");
        }
        colfilename.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        addTable.setItems(list);
        msg.setText("Please Update File Type Column");

    }

    //Edit the File_Type Column 
    @FXML
    public void editTableOnColoumn(TableColumn.CellEditEvent e) {

        Fichier selectedFichier = addTable.getSelectionModel().getSelectedItem();

        selectedFichier.setType(e.getNewValue().toString());

        if (!checkType(e.getOldValue().toString())) {
            msg.setText("File Type Not Supported !");
        } else {
            msg.setText("File Type updated !");
        }

    }

    //Add Data to Database using crud method
    @FXML
    public void addDataToDb(ActionEvent e) throws IOException {
        if (!addTable.getItems().isEmpty() && !userid.getText().equals("")) {
            Fichier f = new Fichier();

            for (int i = 0; i < addTable.getItems().size(); i++) {
                f = addTable.getItems().get(i);

                FichierService fs = new FichierService();

                UserService us = new UserService();
                try {
                    int userId = Integer.parseInt(userid.getText());

                    User user = us.getUserByID(userId);
                    System.out.println(user);
                    if (user.getCin() != null) {
                        Fichier fichier = new Fichier(f.getType(), f.getIdPhysique(), user);
                        System.out.println(fichier);
                        fs.insertFichier(fichier);
                        msg.setText("All Files Added Successfully");
                    } else {
                        msg.setText("User Not Found!");
                    }

                } catch (NumberFormatException ex) {
                    msg.setText("Please Enter A Valid User Id!");
                }
            }
        } else {
            msg.setText("Please Select Files and enter User Id First!");
        }
    }

    //Delete a row from TableView 
    @FXML
    public void deleteRowTabView(ActionEvent e) {
        int SelectedId = addTable.getSelectionModel().getSelectedIndex();
        addTable.getItems().remove(SelectedId);
    }

    //Get File_Type from Choicebox
    @FXML
    public String getTypeFichieChoice(ActionEvent e) {

        String s = typechoice.getValue();
        msg.setText("Type Fichier: " + s);
        return s;
    }

    //Activate ChoiceBox for file type select
    @FXML
    public boolean activateChoiceBox(ActionEvent e) {
        boolean bool = true;

        if (checkchoice.isSelected()) {
            typechoice.setDisable(false);

        } else {
            typechoice.setDisable(true);

        }
        bool = typechoice.isDisabled();

        return bool;

    }

    //Set a chosen type from choicebox for all the uploaded files 
    @FXML
    public void SetAllType(ActionEvent e) throws IOException {
        boolean bool = this.activateChoiceBox(e);
        Fichier f = new Fichier();
list2.clear();
        for (int i = 0; i < addTable.getItems().size(); i++) {
            f = addTable.getItems().get(i);
            String s = f.getIdPhysique();
            System.out.println( addTable.getItems().size()+"  1");
                
            if (!bool) {
                
                System.out.println( addTable.getItems().size()+"  2");
                Fichier f2 = new Fichier();
                f2.setIdPhysique(s);
                f2.setType(this.getTypeFichieChoice(e));
                list2.add(f2);

            } else {
                
                System.out.println( addTable.getItems().size()+"   3");
                Fichier f2 = new Fichier();
                f2.setIdPhysique(s);
                f2.setType("RADIO");
                
                list2.add(f2);
            }

        }
        addTable.getItems().removeAll(list);
        colfilename.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));

        addTable.setItems(list2);
    }

    //clear tableview
    @FXML
    public void clearTable(ActionEvent e) {
        
        addTable.getItems().clear();
     
    }

}
