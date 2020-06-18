/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;

import com.kiddo.Entite.Reclamation;
import com.kiddo.Service.ServiceReclamation;
import com.kiddo.Utils.Email;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author TR3x
 */
public class Kiddo extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        
        ServiceReclamation rec = new ServiceReclamation();
        try {
            
            int a = rec.countReaclamation();
            System.out.println(a);
        } catch (Exception e) {
        }
        launch(args); 
        
        
       
       
       
       
       
       
        
        /*ServiceReclamation Rec = new ServiceReclamation();
        Email e = new Email();
        Reclamation r1 = new Reclamation(0, "incident", "Enfant tombé du 2éme étage ", 0, 8);
        Reclamation r2 = new Reclamation(0, "ahmed ", "mort", 0, 101);
        
        try {
            
        //ajouter reclamation
          // Rec.ajouter(r1);
         //  System.out.println("Reclamation a ete ajouté");
            
        //afficher reclamation  
           // List<Reclamation> list = Rec.readAll();
           // System.out.println(list);
            
        //supprimer reclamation
           // Rec.supprimer(9);
            //System.out.println("Reclamation a ete supprimer");
            
        //modifier reclamation
           //Rec.update(r2, 9);
           //System.out.println("Reclamation a ete modifier");
       
             
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/
         
        
    }
    
    
}
