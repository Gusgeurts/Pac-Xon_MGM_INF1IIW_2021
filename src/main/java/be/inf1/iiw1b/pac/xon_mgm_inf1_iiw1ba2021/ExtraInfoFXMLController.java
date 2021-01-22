package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ExtraInfoFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button StartMenuKnop;

    /**
     * deze methode intisaliseert de scene
     */
    @FXML
    void initialize() {
        StartMenuKnop.setOnAction(this::veranderSchermStartMenu);
        StartMenuKnop.setFocusTraversable(false);

    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de StartMenu knop deze methode zal de stage veranderen naar een
     * andere scene....
     *
     * gehaald van:https://youtube/com/watch?.......
     */
    private void veranderSchermStartMenu(ActionEvent e) {
        toetsGeluid();
        try {
            Parent startMenuParent;
            startMenuParent = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
            Scene startMenuScene = new Scene(startMenuParent);
            Stage startMenuScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
            startMenuScherm.hide();
            startMenuScherm.setScene(startMenuScene);
            startMenuScherm.show();
        } catch (IOException ex) {
        }

    }
    MediaPlayer mediaPlayer;

    public void toetsGeluid() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("button-19.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

    }

}
