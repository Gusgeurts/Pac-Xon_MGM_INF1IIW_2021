package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

        errorText.setVisible(false);                //zorgt ervoor dat de errortext niet zichtbaar is bij opstarten
        startKnop.setOnAction(this::veranderSchermSpeelVeld);         //wanneer de start knop wordt gebruikt wordt verwezen naar de veranderschermspeelveld() methode
        extraInfoKnop.setOnAction(this::veranderSchermExtraInfo);       //wanneer de extrainfo knop wordt gebruikt wordt verwezen naar de veranderschermextrainfo() methode
        stopKnop.setOnAction(this::sluitGame);                            //wanneer de stop knop wordt gebruikt wordt verwezen naar de sluitgame() methode

        startKnop.setFocusTraversable(false);           //zorgt dat de knoppen niet geselecteerd/blauw omringd worden bij een keyevent
        stopKnop.setFocusTraversable(false);
        extraInfoKnop.setFocusTraversable(false);
        moeilijkheidsGraad1.setFocusTraversable(false);
        moeilijkheidsGraad2.setFocusTraversable(false);
        moeilijkheidsGraad3.setFocusTraversable(false);
    }

    /**
     * @param e is de actie/het event van de knop 
     * deze methode is de methode voor de start game knop deze methode zal eerst 
     * kijken welke moeilijkheids graad is geselecteerd en als er een 
     * moeilijkheidsgraad is geselecteerd zal de stage veranderen naar een 
     * andere scene namelijk het speelveld met het aantalspoken afhankelijk van 
     * de geselecteerde moeilijkheidsgraad
     *
     * gehaald van:https://www.youtube.com/watch?v=XCgcQTQCfJQ&t=3s
     */
    public void veranderSchermSpeelVeld(ActionEvent e) {
        knopGeluid();

        if (moeilijkheidsGraad1.isSelected()) {
            aantalSpoken = 1;

        } else if (moeilijkheidsGraad2.isSelected()) {
            aantalSpoken = 3;

        } else if (moeilijkheidsGraad3.isSelected()) {
            aantalSpoken = 6;
            
        } else {                    //als er geen moeilijkheidsgraad is geselecteerd wordt de error test zichtbaar en speelt er een errorgeluid af
            errorGeluid();
            errorText.setVisible(true);
        }
        if (aantalSpoken > 0) {
            speelIntro();                    //zorgt ervoor dat de game intro start bij opstarten van speelveld 
            try {
                Parent speelVeldParent;
                speelVeldParent = FXMLLoader.load(getClass().getResource("speelveld.fxml"));
                Scene speelVeldScene = new Scene(speelVeldParent);
                Stage gameScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
                gameScherm.hide();
                gameScherm.setScene(speelVeldScene);
                gameScherm.show();
            } catch (IOException ex) {
            }
        }
        aantalSpoken = 0;           //zorgt ervoor dat moeilijkheidsgraad opnieuw moet worden geselecteerd
    }

    /**
     * @param e is de actie/het event van de knop 
     * deze methode is de methode voor de extra info knop deze methode zal de 
     * stage veranderen naar een andere scene namelijk de extrainfo
     *
     * gehaald van:https://www.youtube.com/watch?v=XCgcQTQCfJQ&t=3s
     *
     */
    private void veranderSchermExtraInfo(ActionEvent e) {
        knopGeluid();
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
     * @param e is de actie/het event van de knop 
     * deze methode is de methode voor de stop game knop deze methode zal de game afsluiten
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
    
    MediaPlayer mediaPlayer;            //voorkomt verwijderen van methode knopgeluid (garbage collector)

    /**
     * deze methode speelt de intro van de game af gehaald uit de cursus
     */
    public void speelIntro() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("8d82b5_Pacman_Opening_Song_Sound_Effect.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    /**
     * deze methode maakt een geluid bij het gebruiken van een knop
     * gehaald uit de cursus
     */
    public void knopGeluid() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("button-19.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

    }

    /**
     * deze methode maakt een geluid wanneer de moeilijkheidsgraad niet is 
     * geselecteerd namelijk windows error 
     * gehaald uit de cursus
     */
    public void errorGeluid() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("Microsoft_Windows_XP_Error_-_Sound_Effect_HD.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

    }

}
