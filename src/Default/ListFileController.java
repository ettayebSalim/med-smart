/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Fichier;
import Services.FichierService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
    // private TableColumn<Fichier, Integer> coluserid;
    private TableColumn<Fichier, String> coluserid;
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
    public BorderPane borderpanemain;

    @FXML
    private TextField tablefilter;

    @FXML
    private TextField test;

    @FXML
    private ImageView imageView;

    private final String fileout = "C:\\Users\\AGuizani\\Desktop\\med-smart _CodeNameOne\\backNodeCodeNameOne\\backNodeCodeNameOne\\public\\fichiers\\";

    private MenuController menucontroller;

    public ObservableList<Fichier> listFile = FXCollections.observableArrayList();

    public ObservableList<Fichier> listFileTopass = FXCollections.observableArrayList();

    private int n = 0;
    final int m = 25;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fetchNFiles(0, 25);
        listfile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Search File By Id text field  
        filebyid.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                getFileById();
            }
        });

        listfile.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Fichier f = new Fichier(newSelection.getType(), newSelection.getIdPhysique());
                test.setText("Nom Fichier: " + f.getIdPhysique() + "    Type Fichier: " + f.getType());
                previewFile(f.getIdPhysique());
                imageView.setOnMouseClicked(e -> {
                    FullSizeView(imageView.getImage(), f.getIdPhysique());
                });

            }
        });

    }

    public void showTableView() {
        listfile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colfileid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfiletype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colfilename.setCellValueFactory(new PropertyValueFactory<>("IdPhysique"));
        //coluserid.setCellValueFactory(cellData -> new SimpleIntegerProperty((cellData.getValue().getUser().getId())).asObject());
        coluserid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getCin()));// after modifying getUserById UserService
        listfile.setItems(listFile);
    }

    @FXML
    public void fetchFiles() {

        n = 0;
        showTableView();
        listfile.getItems().clear();
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

        showTableView();
        listfile.getItems().clear();
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
        showTableView();
        listfile.getItems().clear();
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
        showTableView();

        try {
            int id = Integer.parseInt(filebyid.getText());
            try {
                Fichier fichier = fs.getFichierById(id);
                System.out.println(fichier);
                listfile.getItems().clear();
                listFile.add(fichier);
                showTableView();
            } catch (NullPointerException npe) {
                listfile.getItems().clear();
                listfile.setPlaceholder(new Label("File Not Found"));
            }
        } catch (NumberFormatException ex) {
            listfile.getItems().clear();
            listfile.setPlaceholder(new Label("Please enter only valid Integer "));
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

    @FXML
    public void SearchTableByTypeOrUser() {

        FilteredList<Fichier> filteredData = new FilteredList(listFile, bool -> true);
        tablefilter.textProperty().addListener((ObservableValue<? extends String> obervable, String oldValue, String newValue) -> {

            filteredData.setPredicate(fichier -> {
                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                String userCIN = String.valueOf(fichier.getUser().getCin());
                if (fichier.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (fichier.getIdPhysique().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (userCIN.toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Fichier> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(listfile.comparatorProperty());
        ObservableList<Fichier> listFile = sortedData;
        listfile.setItems(listFile);

    }

    public void previewFile(String p) {

        String extension = null;

        File file = new File(fileout + p);
        String filePath = fileout + p;
        if (file.exists()) {
            if (filePath.lastIndexOf(".") > 0) {
                extension = filePath.substring(filePath.lastIndexOf(".") + 1);
            }

            if ("png".equalsIgnoreCase(extension)
                    || "jpg".equalsIgnoreCase(extension)
                    || "jpeg".equalsIgnoreCase(extension)
                    || "tif".equalsIgnoreCase(extension)) {
                try {

                    FileInputStream inputstream = new FileInputStream(filePath);
                    Image myImage = new Image(inputstream);

                    imageView.setImage(myImage);

                } catch (IOException ex) {
                    ex.getStackTrace();
                }
            } else if ("pdf".equalsIgnoreCase(extension)) {
                try {
                    PDDocument doc = PDDocument.load(file);
                    PDFRenderer renderer = new PDFRenderer(doc);

                    BufferedImage image = renderer.renderImage(0);
                    Image img = SwingFXUtils.toFXImage(image, null);
                    imageView.setImage(img);

                    doc.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                Image myImage = new Image(getClass().getResourceAsStream("/icons/NoPreview.png"));
                imageView.setImage(myImage);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Not Found");
            alert.setHeaderText("File Not Found ! ");
            ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOk);
            Optional<ButtonType> result = alert.showAndWait();

            alert.showAndWait();

        }
    }

    @FXML
    private void FullSizeView(Image img, String s) {

        ImageView imageViewer = new ImageView(img);

        imageViewer.setFitHeight(720);
        imageViewer.setFitWidth(1280);
        imageViewer.setPreserveRatio(true);
        imageViewer.setSmooth(true);
        imageViewer.setCache(true);
        imageViewer.autosize();
        System.out.println(imageViewer.getBoundsInParent().getWidth());
        System.out.println(imageViewer.getBoundsInParent().getHeight());
        Group root = new Group(imageViewer);
        Scene scene = new Scene(root, imageViewer.getBoundsInParent().getWidth(), imageViewer.getBoundsInParent().getHeight());
        System.out.println(scene.getWidth());
        System.out.println(scene.getHeight());
        String css = getClass().getResource("StyleCSS.css").toExternalForm();
        scene.getStylesheets().clear();

        scene.getStylesheets().add(css);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Full View of " + s);
        stage.setResizable(true);

        stage.show();

    }

}
