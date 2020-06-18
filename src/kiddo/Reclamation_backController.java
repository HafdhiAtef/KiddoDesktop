/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;

import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import com.kiddo.Entite.Reclamation;
import com.kiddo.Service.ServiceReclamation;
import com.kiddo.Service.Fosuserservice;
import com.kiddo.Utils.Email;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TR3x
 */
public class Reclamation_backController implements Initializable {

    @FXML
    private ObservableList<Reclamation> data;
    @FXML
    public  TableView<Reclamation> tabR;
    @FXML
    private TableColumn<?, ?> id_u;
    @FXML
    public TableColumn<?, ?> tit;
    @FXML
    private TableColumn<?, ?> cont;
     @FXML
    private TextField contMail;
     
    
    private ObservableList<Reclamation> recdata = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficheReclamationAction();
                             
        }catch (IOException ex) {
            Logger.getLogger(Reclamation_backController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void AfficheReclamationAction () throws IOException
    {
         try {
            id_u.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            tit.setCellValueFactory(new PropertyValueFactory<>("titre"));
            cont.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            

            ServiceReclamation reclamation = new ServiceReclamation();
            List<Reclamation> list = reclamation.readAll();
            data = FXCollections.observableArrayList(list);
            tabR.setItems(data);
                             
        } catch (SQLException e) {
        }
    }
    
    @FXML
    private void ResourdreAction(ActionEvent event) throws IOException, Exception {
        String m=contMail.getText();
        if(m.isEmpty()){
            System.out.println("fer8aaaaaaaa");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifiez Vos Coordonnees !!!");
            alert.showAndWait();
            
            Parent demandeclub = FXMLLoader.load(getClass().getResource("Reclamation_back.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
        }
        else{
        Fosuserservice user = new Fosuserservice();
        ServiceReclamation reclamation = new ServiceReclamation();
        Reclamation myclass = tabR.getSelectionModel().getSelectedItem();
        String mail = user.getMailbyId(myclass.getId_user());
               System.out.println(mail);
               System.out.println(contMail.getText());
         try {
             Email.sendMail(mail,contMail.getText());
             reclamation.supprimer(myclass.getId());            
             AfficheReclamationAction();
         } catch (SQLException ex) {
             System.out.println(ex);
         }     
    }
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
