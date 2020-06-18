/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;


/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;*/
//import com.kiddo.Entite.Reclamation;
import com.kiddo.Entite.Atelier;
import com.kiddo.Service.Fosuserservice;
import com.kiddo.Service.ServiceAtelier;
import com.kiddo.Service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author TR3x
 */
public class Atelier_frontController implements Initializable {
    public static final String ACCOUNT_SID =
            "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static final String AUTH_TOKEN =
            "your_auth_token";
    @FXML
    private ObservableList<Atelier> data;
    @FXML
    public  TableView<Atelier> tabR;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    public TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> date_debut;
    @FXML
    private TableColumn<?, ?> date_fin;
     @FXML
    private TextField tel;
     
     private final ObservableList<Atelier> recdata = FXCollections.observableArrayList();
     
   // @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("intialize");
        try {
            try {
                AfficheAtelierAction();
            } catch (ParseException ex) {
                Logger.getLogger(Atelier_frontController.class.getName()).log(Level.SEVERE, null, ex);
            }
                             
        }catch (IOException ex) {
            Logger.getLogger(Atelier_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     
    
    

    

    @FXML
    private void AfficheAtelierAction () throws IOException, ParseException
    {
         try {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            

            
            ServiceAtelier atelier = new ServiceAtelier();
            List<Atelier> list = atelier.readAll();
             System.out.println(list);
            data = FXCollections.observableArrayList(list);
            tabR.setItems(data);
                             
        } catch (SQLException e) {
        }
    }
    
    @FXML
    private void AbonnerAction(ActionEvent event) throws IOException, Exception {
            
        Fosuserservice user = new Fosuserservice();
        ServiceReclamation reclamation = new ServiceReclamation();
        String num;
        num = tel.getText();
        
        if(num.isEmpty()){
            System.out.println("fer8aaaaaaaa");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifiez les numéro !!!");
            alert.showAndWait();
            
            Parent demandeclub = FXMLLoader.load(getClass().getResource("Reclamation_back.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
        }
        Atelier myclass = tabR.getSelectionModel().getSelectedItem();
       // SMS sms= new SMS();
        /* Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+14159352345"), // to
                        new PhoneNumber("+14158141829"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());
        */
        // Find your Account Sid and Auth Token at twilio.com/console
    

    
        
    
        
        
        //String nom = user.getNombyId(myclass.getId_user())  ;
        
        
            /*   System.out.println(mail);
               System.out.println(contMail.getText());
         try {
             Email.sendMail(mail,contMail.getText());
             reclamation.supprimer(myclass.getId());            
             AfficheReclamationAction();
         } catch (SQLException ex) {
             System.out.println(ex);
         }
         */
         
         
    }
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        Parent demandeclub = FXMLLoader.load(getClass().getResource("front.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
    }
    
    
}
