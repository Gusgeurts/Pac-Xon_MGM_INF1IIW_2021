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
    private static int aantalSpoken;

    /**
     * deze methode intisaliseert de scene
     */
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

    /**
     * @param k is de actie/het event van de knop deze methode is de methode
     * voor de start game knop deze methode zal eerst kijken welke moeilijkheids
     * graad is geselecteerd en als er een moeilijkheidsgraad is geselecteerd
     * zal de stage veranderen naar een andere scene namelijk het speelveld met
     * het aantalspoken afhankelijk van de geselecteerde moeilijkheidsgraad
     *
     * gehaald van:...
     */
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
                Parent speelVeldParent;
                speelVeldParent = FXMLLoader.load(getClass().getResource("speelveld.fxml"));
                Scene speelVeldScene = new Scene(speelVeldParent);
                Stage gameScherm = (Stage) ((Node) k.getSource()).getScene().getWindow();
                gameScherm.hide();
                gameScherm.setScene(speelVeldScene);
                gameScherm.show();
            } catch (IOException ex) {
            }
        }
        aantalSpoken = 0;
    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de extra info knop deze methode zal de stage veranderen naar een
     * andere scene namelijk de extrainfo
     *
     * gehaald van:...
     *
     */
    private void veranderSchermExtraInfo(ActionEvent e) {
        try {
            Parent extraInfoParent;
            extraInfoParent = FXMLLoader.load(getClass().getResource("extraInfoFXML.fxml"));
            Scene extraInfoScene = new Scene(extraInfoParent);
            Stage extraInfoScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
            extraInfoScherm.hide();
            extraInfoScherm.setScene(extraInfoScene);
            extraInfoScherm.show();
        } catch (IOException ex) {
        }
    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methoden
     * voor de stop game knop deze methode zal de game afsluiten
     */
    private void sluitGame(ActionEvent e) {
        System.exit(0);
    }

    /**
     * @return deze methode geeft het aantalspoken terug
     */
    public static int getAantalSpoken() {
        return aantalSpoken;
    }
}
