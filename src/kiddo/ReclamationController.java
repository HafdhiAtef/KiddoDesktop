/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiddo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author TR3x
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField titreInput;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> contenu;
    @FXML
    private TableColumn<?, ?> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add(ActionEvent event) {
    }

    @FXML
    private void tableClick(MouseEvent event) {
    }
    
}
