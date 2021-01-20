package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Spoken;

public class StartMenuController {

    @FXML
    private Button startKnop;

    @FXML
    private Button stopKnop;

    @FXML
    private Button extraInfoKnop;

    @FXML
    private RadioButton moeilijkheidsGraad1;

    @FXML
    private RadioButton moeilijkheidsGraad2;

    @FXML
    private RadioButton moeilijkheidsGraad3;

    @FXML
    private Text errorText;
    public static int aantalSpoken;

    @FXML
    void initialize() {
        errorText.setVisible(false);
        startKnop.setOnAction(this::veranderSchermSpeelVeld);
        extraInfoKnop.setOnAction(this::veranderSchermExtraInfo);
        stopKnop.setOnAction(this::sluitGame);

        startKnop.setFocusTraversable(false);
        stopKnop.setFocusTraversable(false);
        extraInfoKnop.setFocusTraversable(false);
        moeilijkheidsGraad1.setFocusTraversable(false);
        moeilijkheidsGraad2.setFocusTraversable(false);
        moeilijkheidsGraad3.setFocusTraversable(false); 
    }

    public void veranderSchermSpeelVeld(ActionEvent k) {
        if (moeilijkheidsGraad1.isSelected()) {
            aantalSpoken = 1;

        } else if (moeilijkheidsGraad2.isSelected()) {
            aantalSpoken = 2;

        } else if (moeilijkheidsGraad3.isSelected()) {
            aantalSpoken = 3;
        } else {
            errorText.setVisible(true);
        }
        if (aantalSpoken > 0) {
            try {
                Parent speelVeldParent = null;
                speelVeldParent = FXMLLoader.load(getClass().getResource("speelveld.fxml"));
                Scene speelVeldScene = new Scene(speelVeldParent);
                Stage gameScherm = (Stage) ((Node) k.getSource()).getScene().getWindow();
                gameScherm.hide();
                gameScherm.setScene(speelVeldScene);
                gameScherm.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        aantalSpoken=0;
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

    private void sluitGame(ActionEvent e) {
        System.exit(0);
    }

    public static int getAantalSpoken() {
        return aantalSpoken;
    }
}
