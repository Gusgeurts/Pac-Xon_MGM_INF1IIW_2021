package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartMenuController {

    @FXML
    private Button startKnop;

    @FXML
    private Button stopKnop;

    @FXML
    private Button extraInfoKnop;

    
    @FXML
    void initialize() {
                               
        startKnop.setOnAction(this::veranderSchermSpeelVeld);
        extraInfoKnop.setOnAction(this::veranderSchermExtraInfo);
        stopKnop.setOnAction(this::sluitGame);
        
        startKnop.setFocusTraversable(false);
        stopKnop.setFocusTraversable(false);
        extraInfoKnop.setFocusTraversable(false);
              
    }
   
       
    private void veranderSchermSpeelVeld(ActionEvent e) {
        try {
            Parent speelVeldParent = null;
            speelVeldParent = FXMLLoader.load(getClass().getResource("speelveld.fxml"));
            Scene speelVeldScene = new Scene(speelVeldParent);
            Stage gameScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
            gameScherm.hide();
            gameScherm.setScene(speelVeldScene);
            gameScherm.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }    
    }
    
    private void veranderSchermExtraInfo(ActionEvent e) {
        try {
            Parent extraInfoParent = null;
            extraInfoParent = FXMLLoader.load(getClass().getResource("extraInfoFXML.fxml"));
            Scene extraInfoScene = new Scene(extraInfoParent);
            Stage extraInfoScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
            extraInfoScherm.hide();
            extraInfoScherm.setScene(extraInfoScene);
            extraInfoScherm.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }       
    }
    
    
    private void sluitGame(ActionEvent e){
        System.exit(0);
    }
   
}
