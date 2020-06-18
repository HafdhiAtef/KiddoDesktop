/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;

import com.kiddo.Entite.Reclamation;
import com.kiddo.Service.Fosuserservice;
import com.kiddo.Service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TR3x
 */
public class Reclamation_frontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField titre;
    @FXML
    private TextField contenu;
    
    @FXML
    private Button retour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        Parent demandeclub = FXMLLoader.load(getClass().getResource("front.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void addAction(ActionEvent event) throws IOException, SQLException {
        
        ServiceReclamation rec = new ServiceReclamation();
        int a = rec.countReaclamation();
        if(a==3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Vous avez dépasser 3 reclamation !!!");
            alert.showAndWait();
            
             Parent demandeclub = FXMLLoader.load(getClass().getResource("Reclamation_front.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
        }
        else{
        String tit=titre.getText(),cont=contenu.getText();
        if(tit.isEmpty() || cont.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifier votre Réclamation !!!");
            alert.showAndWait();
            
             Parent demandeclub = FXMLLoader.load(getClass().getResource("Reclamation_front.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show();
        }
        ServiceReclamation ser= new ServiceReclamation();
        Reclamation r=new Reclamation();
        Fosuserservice user = new Fosuserservice();
        
        r.setTitre(titre.getText());
        r.setContenu(contenu.getText());
        r.setId_user(1);
        r.setEtat(0);
        
        try {
            ser.ajouter(r);
            System.out.println("ajout done");
        } catch (SQLException ex) {
            System.out.println("reclamation non ajouté");
        }
       Parent demandeclub = FXMLLoader.load(getClass().getResource("front.fxml"));
        Scene tableViewScene = new Scene(demandeclub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(tableViewScene);
        window.show(); 
        }
    }
            
            
        
    

}
