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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import static Services.TypeFichierService.checkType;
import Services.UserService;
import Utiles.MyConnection;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
    private TableColumn<Fichier, String> colfilename;

    @FXML
    private TableColumn<Fichier, String> coltype;

    @FXML
    private TextField userid;

    @FXML
    private CheckBox checkchoice;

    @FXML
    private ChoiceBox<String> typechoice;

    private ObservableList<Fichier> list = FXCollections.observableArrayList();
    private List<Path> fileAbsPath = new ArrayList();
    private String[] choice = {"RADIO", "SCANNER", "IRM", "ECHO", "ANALYSE_LABO", "ORDONNANCE", "LETTRE_DE_LIAISON"};

    File currentDir = new File("../");
    private final String fileout = currentDir.getAbsolutePath() + "\\backnameone\\backNodeCodeNameOne\\public\\fichiers\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // setting the File_Type column editable
        addTable.setEditable(true);
        coltype.setCellFactory(TextFieldTableCell.forTableColumn());
        // set selection mode to multiple rows selection
        addTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //setting-up choiceboxx

        
        typechoice.getItems().addAll(choice);
        typechoice.setOnAction(this::getTypeFichieChoice);

    }

    //select Files from Local Drive
    @FXML
    public void selectMultipleFile(ActionEvent e) throws IOException {
        FileChooser fc = new FileChooser();

        List<File> selectedFile = fc.showOpenMultipleDialog(null);
        
        if (selectedFile != null) {
            for (int i = 0; i < selectedFile.size(); i++) {

                String s = selectedFile.get(i).getPath();
                fileAbsPath.add(selectedFile.get(i).toPath());

                if (typechoice.getValue()!=null) {
                    list.add(new Fichier(this.getTypeFichieChoice(e), s));
                } else {
                    list.add(new Fichier("radio", s));
                }
            }

        } else {
            msg.setText("File Not Valid");
            alertFile(" File Error !","File Not Valid !");
        }
        colfilename.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        addTable.setItems(list);
        System.out.println(fileAbsPath);
        

    }

    //Edit the File_Type Column 
    @FXML
    public void editTableOnColoumn(TableColumn.CellEditEvent e) {

        Fichier selectedFichier = addTable.getSelectionModel().getSelectedItem();

        selectedFichier.setType(e.getNewValue().toString());

        if (!checkType(e.getOldValue().toString())) {
            msg.setText("File Type Not Supported !");
            alertFile("File Type Error !","File Type Not Supported !");
        } else {
            msg.setText("File Type updated !");
        }

    }

    //Add Data to Database using crude method
    @FXML
    public void addDataToDb(ActionEvent e) throws IOException {
        boolean b1 = false;
        boolean b2 = false;
        if (!addTable.getItems().isEmpty() && !userid.getText().equals("")) {

            Fichier f = new Fichier();
            //Saving file details to DB

            for (int i = 0; i < addTable.getItems().size(); i++) {
                f = addTable.getItems().get(i);
                Path var = fileAbsPath.get(i);
                String s = var.toString();
                File f2 = new File(s);
                File fil = new File(fileout + f2.getName());
                FichierService fs = new FichierService();
                UserService us = new UserService();
                //  try {
                //int userId = Integer.parseInt(userid.getText());
                //User user = us.getUserByID(userId);
                String userCIN = userid.getText();
                User user = this.getUserByCIN(userCIN);
                if (user.getId() != 0) {

                    Fichier fichier = new Fichier(f.getType(), f.getIdPhysique(), user);
                    b2 = saveFile(var);
                    if (b2) {
                        fs.insertFichier(fichier);
                        b1 = true;
                    } else {
                        b1 = false;
                        break;
                    }

                } else {
                    msg.setText(" Invalid User CIN or User Not Found!");
                    alertFile("User Id Error !"," Invalid User CIN or User Not Found!");
                    break;
                }


                /*  } catch (NumberFormatException ex) {
                    msg.setText("Please Enter A Valid User Id!");
                }*/
            }
            if (b1 && b2) {
                addTable.getItems().clear();
                fileAbsPath.clear();
                msg.setText(" ALL Files Added Successfully");
            }

        } else {
            msg.setText("Please Select Files and enter User Id First!");
        }

    }

    //Delete a row from TableView 
    @FXML
    public void deleteRowTabView(ActionEvent e) {
        ObservableList<Fichier> selectedItems = addTable.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {

            for (int i = 0; i < selectedItems.size(); i++) {
                Fichier f = selectedItems.get(i);
                int idx = -1;
                for (Path p : fileAbsPath) {
                    if (p.getFileName().toString().equals(f.getIdPhysique())) {
                        idx = fileAbsPath.indexOf(p);
                    }
                }
                fileAbsPath.remove(idx);
            }
            addTable.getItems().removeAll(selectedItems);
            System.out.println(fileAbsPath);
        } else {
            msg.setText("Please Select a Row(s) First!");
        }
    }

    //Get File_Type from Choicebox
    @FXML
    public String getTypeFichieChoice(ActionEvent e) {

        String s = typechoice.getValue();
        msg.setText("Type Fichier: " + s);
        return s;
    }



    //Set a chosen type from choicebox for  the selected files 
    @FXML
    public void SetSelectedType(ActionEvent e) throws IOException {
        
        ObservableList<Fichier> list2 = FXCollections.observableArrayList();
        ObservableList<Fichier> selectedItems = addTable.getSelectionModel().getSelectedItems();
        ObservableList<Integer> selectedIndex = addTable.getSelectionModel().getSelectedIndices();
        list2.addAll(list);
        if (!selectedItems.isEmpty()) {
            if (typechoice.getValue()!=null) {
                for (int i = 0; i < selectedItems.size(); i++) {

                    Fichier f = selectedItems.get(i);

                    Fichier f2 = new Fichier();
                    f2.setIdPhysique(f.getIdPhysique());
                    f2.setType(this.getTypeFichieChoice(e));
                    list2.set(selectedIndex.get(i).intValue(), f2);
                }

            } else {
                msg.setText("Please Set File type Choice First!");
            }

        } else {
            msg.setText("Please Select a Row(s) First!");
        }
        list.clear();
        list.addAll(list2);
        colfilename.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        System.out.println(addTable.getItems().size());
        addTable.setItems(list);

    }

    //clear tableview
    @FXML
    public void CancelAdd(ActionEvent e) {

        addTable.getItems().clear();
        fileAbsPath.clear();
        System.out.println(fileAbsPath);
    }

    //save a File
    private boolean saveFile(Path p) {

        String extension = null;
        String filePath = p.toString();
        File fsource = new File(filePath);
        File ftarget = new File(fileout + fsource.getName());
        File folder = new File(fileout);;

        boolean save = false;

        if (filePath.lastIndexOf(".") > 0) {
            extension = filePath.substring(filePath.lastIndexOf(".") + 1);
        }
        if (folder.isDirectory()) {
            if (fsource.exists() && fsource.isFile()) {
                if (!ftarget.exists()) {

                    if ("png".equalsIgnoreCase(extension)
                            || "jpg".equalsIgnoreCase(extension)
                            || "jpeg".equalsIgnoreCase(extension)
                            || "tif".equalsIgnoreCase(extension)) {
                        try {
                            BufferedImage image = ImageIO.read(fsource);
                            int height = image.getHeight();
                            int width = image.getWidth();
                            ImageIO.write(image, "png", ftarget);
                            save = true;
                        } catch (IOException ex) {
                            ex.getStackTrace();
                        }
                    } else if ("pdf".equalsIgnoreCase(extension)) {
                        try {
                            PDDocument doc = PDDocument.load(fsource);
                            doc.save(ftarget);
                            doc.close();
                            save = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if ("docx".equalsIgnoreCase(extension)) {
                        try {
                            InputStream fis = new FileInputStream(fsource);
                            XWPFDocument docu = new XWPFDocument(fis);
                            OutputStream stream = new FileOutputStream(ftarget);
                            docu.write(stream);
                            save = true;
                            stream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        save = false;
                        msg.setText("The File Format Is Not Supported !");
                        alertFile("File extension Error !","The File Format Is Not Supported !");
                    }

                } else {
                    save = false;
                    msg.setText("The File " + fsource.getName() + " Already Exists in Destination Folder !");
                    alertFile("Duplicate File Error !","The File " + fsource.getName() + " Already Exists in Destination Folder !");
                }
            } else {
                save = false;
                msg.setText("The File " + fsource.getName() + "  does not exist or is not a File !");
               alertFile("Source File Error !","The File " + fsource.getName() + "  does not exist or is not a File !");
            }
        } else {
            save = false;
            msg.setText("The Folder " + folder.getName() + " does not Exist !");
            alertFile("Folder Not Found !","The Folder " + folder.getName() + " does not Exist !");
        }
        return save;
    }

    //Select User by CIN
    public User getUserByCIN(String cin) {
        Connection cnx = MyConnection.getInstance().getCnx();
        User user = new User();
        char ch = '"';

        try {
            String req = "SELECT * from user WHERE cin =?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, cin);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setCin(rs.getString(5));
                user.setHashedPwd(rs.getString(6));
                user.setNumtel(rs.getString(7));
                user.setRole(rs.getString(8));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }

        return user;
    }
    
    public Alert alertFile(String title,String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(title);
                    alert.setHeaderText(msg);                   
                    ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll( buttonTypeOk);
                    Optional<ButtonType> result = alert.showAndWait();
                    return alert;
    }

}
