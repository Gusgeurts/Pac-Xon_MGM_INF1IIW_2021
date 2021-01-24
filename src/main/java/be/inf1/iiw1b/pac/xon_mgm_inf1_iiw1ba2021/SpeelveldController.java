package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Mannetje;
import model.Speelveld;
import model.Spoken;
import model.Spook;
import model.Vak;
import model.Vakken;
import view.MannetjeView;
import view.SpeelveldView;
import view.SpokenView;

public class SpeelveldController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane speelveld;

    @FXML
    private Button resetButton;

    @FXML
    private Label labelLevens;

    @FXML
    private Label labelInvulProcent;

    @FXML
    private Button StartMenuKnop;

    @FXML
    private Button startKnop;

    @FXML
    private Button stopKnop;

    private Mannetje mannetje;
    private MannetjeView mannetjeView;
    private Spook spook;
    private Spoken spoken;
    private Vak vak;
    private Vakken vakkenVeld;
    private Speelveld vakkenSpeelveld;
    private SpeelveldView vakkenSpeelveldView;
    private SpokenView spokenView;
    private boolean start = false;
    private Timer timer;

    /**
     * deze methode intisaliseert de scene
     */
    @FXML
    void initialize() {
        vakkenVeld = new Vakken(23, 34);             //dit maakt een raster van 23 vakken horizontaal(rijen) en 34 vakken verticaal(kolommen) aan 
        mannetje = new Mannetje(10, 10, vakkenVeld);          //dit spawnt het mannetje in de linkerbovenhoek
        spook = new Spook(0, 0);
        vak = new Vak();
        spoken = new Spoken(vak, vakkenVeld);
        vakkenSpeelveld = new Speelveld(vakkenVeld, mannetje, spoken);

        mannetjeView = new MannetjeView(mannetje);
        spokenView = new SpokenView(spoken);
        vakkenSpeelveldView = new SpeelveldView(vak, vakkenVeld, vakkenSpeelveld, mannetje, spook, spoken, spokenView, mannetjeView);

        speelveld.getChildren().addAll(vakkenSpeelveldView, mannetjeView, spokenView);

        update();

        resetButton.setOnAction(this::reset);                           //wanneer de reset knop wordt gebruikt wordt verwezen naar de reset() methode
        startKnop.setOnAction(this::start);                               //wanneer de start knop wordt gebruikt wordt verwezen naar de start() methode
        StartMenuKnop.setOnAction(this::veranderSchermStartMenu);           //wanneer de startmenu knop wordt gebruikt wordt verwezen naar de veranderschermstartmenu() methode
        stopKnop.setOnAction(this::sluitGame);                             //wanneer de stop knop wordt gebruikt wordt verwezen naar de sluitgame() methode

        mannetjeView.setFocusTraversable(true);         //zorgt ervoor dat de focus op het mannetje ligt

        resetButton.setFocusTraversable(false);         //zorgt dat de knoppen niet geselecteerd/blauw omringd worden bij een keyevent
        StartMenuKnop.setFocusTraversable(false);
        stopKnop.setFocusTraversable(false);
        resetButton.setFocusTraversable(false);
        startKnop.setFocusTraversable(false);

    }

    /**
     * deze methode update het speelveld, update de view van mannetje en spoken
     * alsook de labels voor levens/procent wanneer de start knop wordt gebruikt
     * zal het speelveld actief worden en geupdate worden
     */
    public void update() {
        mannetjeView.update();
        spokenView.update();

        if (start) {

            speelveld.setOnKeyPressed(this::loopRond);

            vakkenSpeelveld.updateSpeelveld();

            vakkenSpeelveldView.spookRaaktGevuld();
        }
        vakkenSpeelveldView.update();

        labelLevens.setText(mannetje.getLevens() + "");
        labelInvulProcent.setText(vakkenSpeelveld.getProcentGevuld() + " %");

    }

    /**
     * @param t is de actie/het event van de knop deze methode zorgt ervoor dat
     * wanneer de pijltjes worden gebruikt het mannetje beweegt
     */
    private void loopRond(KeyEvent t) {
        if (start) {
            switch (t.getCode()) {
                case RIGHT:
                    mannetje.rechts();
                    mannetje.setMaxXBorder();
                    if (vakkenSpeelveld.isVakMannetjeGevuld()) {        //als mannetje op gevuld vak staat --> stil staan als dit niet is --> begin te bewegen
                        mannetje.setVx(0);
                        mannetje.setVy(0);
                    } else {
                        mannetje.setVx(1);
                        mannetje.setVy(0);
                    }
                    mannetjeView.getVormMannetje().setRotate(0);
                    break;
                case LEFT:
                    mannetje.links();
                    mannetje.setMinXBorder();
                    if (vakkenSpeelveld.isVakMannetjeGevuld()) {
                        mannetje.setVx(0);
                        mannetje.setVy(0);
                    } else {
                        mannetje.setVx(-1);
                        mannetje.setVy(0);
                    }
                    mannetjeView.getVormMannetje().setRotate(180);
                    break;
                case UP:
                    mannetje.boven();
                    mannetje.setMinYBorder();
                    if (vakkenSpeelveld.isVakMannetjeGevuld()) {
                        mannetje.setVx(0);
                        mannetje.setVy(0);
                    } else {
                        mannetje.setVx(0);
                        mannetje.setVy(-1);
                    }
                    mannetjeView.getVormMannetje().setRotate(270);
                    break;

                case DOWN:
                    mannetje.onder();
                    mannetje.setMaxYBorder();
                    if (vakkenSpeelveld.isVakMannetjeGevuld()) {
                        mannetje.setVx(0);
                        mannetje.setVy(0);
                    } else {
                        mannetje.setVx(0);
                        mannetje.setVy(1);
                    }
                    mannetjeView.getVormMannetje().setRotate(90);
                    break;
            }
            update();
        }
    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de reset knop, de methode reset het speelveld en update de view
     */
    private void reset(ActionEvent e) {
        knopGeluid();
        if (start) {
            mannetje.resetGame();
            vakkenSpeelveld.resetVeld();
            mannetjeView.getVormMannetje().setRotate(0);
            timer.cancel();

            update();
        }
        start = false;

    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de start knop, de methode start het bewegen van de spoken en het
     * mannetje
     */
    private void start(ActionEvent e) {
        knopGeluid();

        if (!start) {
            timer = new Timer(true);
            for (Spook s : spoken.getSpoken()) {                        //start voor elk spook een timer
                BeweegSpook taskSpook = new BeweegSpook(s, this);
                timer.scheduleAtFixedRate(taskSpook, 0, 16);
            }

            BeweegMannetje taskMannetje = new BeweegMannetje(mannetje, this);       //start voor het mannetje een timer
            timer.scheduleAtFixedRate(taskMannetje, 0, 150);

            start = true;

        }

    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de StartMenu knop, deze methode zal de stage veranderen naar een
     * andere scene namelijk het startmenu scherm gehaald
     * van:https://www.youtube.com/watch?v=XCgcQTQCfJQ&t=3s
     */
    private void veranderSchermStartMenu(ActionEvent e) {
        knopGeluid();
        try {
            Parent startMenuParent;
            startMenuParent = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
            Scene startMenuScene = new Scene(startMenuParent);
            Stage startMenuScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
            startMenuScherm.hide();
            startMenuScherm.setScene(startMenuScene);
            startMenuScherm.show();

            timer.cancel();
        } catch (IOException | NullPointerException ex) {
        }

    }

    MediaPlayer mediaPlayer;            //voorkomt verwijderen van methode knopgeluid (garbage collector)

    /**
     * deze methode maakt een geluid elke keer wanneer je op een knop klikt
     */
    public void knopGeluid() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("button-19.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de stopknop, en sluit de game af
     */
    private void sluitGame(ActionEvent e) {
        System.exit(0);
    }

}
