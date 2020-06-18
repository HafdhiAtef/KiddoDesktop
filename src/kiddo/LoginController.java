/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;

import com.kiddo.Service.Fosuserservice;
import com.kiddo.Service.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jha
 */
public class LoginController implements Initializable {

    @FXML
    private TextField t1;
    @FXML
    private PasswordField t2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SeConnecter(ActionEvent event) throws IOException {
        
        String username = t1.getText();
        String motpass = t2.getText();
        Fosuserservice  sc = new Fosuserservice ();
        if (sc.login(username, motpass)) {
            String role = sc.getRolebyId(sc.getIdbymail(username));
            System.out.println("rooole: "+role);
 String usr = sc.getUsenamebyId(sc.getIdbymail(username));
            if (role.equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}")) {
                System.out.println("admiin connected");
               UserSession.getInstace(usr,username);

                 FXMLLoader loader= new FXMLLoader(getClass().getResource("back.fxml"));
            Parent root= loader.load();
  
            t1.getScene().setRoot(root);
            
            }
            else  if (role.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
                System.out.println("Client is connected ");
                 UserSession.getInstace(usr,username);
//admin
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("front.fxml"));
            Parent root= loader.load();
            
            t1.getScene().setRoot(root);
            }
        }  else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifiez Vos Coordonnees !!!");
            alert.showAndWait();
        }
    }
    
}
