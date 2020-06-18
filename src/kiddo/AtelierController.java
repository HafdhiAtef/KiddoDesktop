/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;

import com.kiddo.Entite.Atelier;
import com.kiddo.Entite.Reclamation;
import com.kiddo.Service.ServiceAtelier;
import com.kiddo.Service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jha
 */
public class AtelierController implements Initializable {

    @FXML
    private TextField titreInput;
    @FXML
    private TextArea contenuInput;
    @FXML
    private TableView<Atelier> table;
    @FXML
    private TableColumn<Atelier, String> nom;
    @FXML
    private TableColumn<Atelier, String> description;
    @FXML
    private TableColumn<Atelier, String> ddebut;
    @FXML
    private TableColumn<Atelier, String> dfin;
    @FXML
    private TextField debut;
    @FXML
    private TextField fin;

    private ObservableList<Atelier> recdata = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
            @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        List<Atelier> listRec= new ArrayList<Atelier>();
        ServiceAtelier rs =  new ServiceAtelier();
        
        
        try {
            listRec = rs.readAll();
        } catch (SQLException ex) {
            //Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            //Logger.getLogger(AtelierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        
        description.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        
        ddebut.setCellValueFactory(
            new PropertyValueFactory<>("date_debut")
        );
        
        dfin.setCellValueFactory(
            new PropertyValueFactory<>("date_fin")
        );
    }    

    @FXML
    private void Add(ActionEvent event) throws SQLException, ParseException {
        Atelier r = new Atelier();
        r.setNom(titreInput.getText());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        r.setDate_debut(debut.getText());
        r.setDate_fin(fin.getText());
        r.setDescription(contenuInput.getText());
        r.setPath("");
        ServiceAtelier rs = new ServiceAtelier();
        rs.ajouter(r);
        
        List<Atelier> listRec= new ArrayList<Atelier>();
        
        
        try {
            listRec = rs.readAll();
        } catch (SQLException ex) {
           // Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException, ParseException {
        //Atelier r = new Atelier();
        Atelier r= table.getSelectionModel().getSelectedItem();
        System.out.println(r);
        r.setNom(titreInput.getText());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        r.setDate_debut(debut.getText());
        r.setDate_fin(fin.getText());
        r.setDescription(contenuInput.getText());
        r.setPath("");
        ServiceAtelier rs = new ServiceAtelier();
        rs.update(r,r.getId());
       // rs.update(r, 0)
        List<Atelier> listRec= new ArrayList<Atelier>();
        
        
        try {
            listRec = rs.readAll();
        } catch (SQLException ex) {
           //erreur try rs.readAll
        }
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }


    @FXML
    private void Delete(ActionEvent event) throws SQLException, ParseException {
        Atelier r= table.getSelectionModel().getSelectedItem();
        ServiceAtelier rs = new ServiceAtelier();
        rs.supprimer(r.getId());
        List<Atelier> listRec= new ArrayList<Atelier>();
        
        
        try {
            listRec = rs.readAll();
        } catch (SQLException ex) {
           // Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }

    @FXML
    private void tableClick(MouseEvent event) {
        Atelier p= table.getSelectionModel().getSelectedItem();
        titreInput.setText(p.getNom());
        contenuInput.setText(p.getDescription());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
df = df.withLocale( Locale.FRANCE);
        debut.setText(p.getDate_debut());
        fin.setText(p.getDate_fin());
    }

    @FXML
    private void pdf(ActionEvent event) {
        ServiceAtelier sa = new ServiceAtelier();
        sa.getPDF();
    }
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        Parent demandeclub = FXMLLoader.load(getClass().getResource("back.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
    }
    
}
