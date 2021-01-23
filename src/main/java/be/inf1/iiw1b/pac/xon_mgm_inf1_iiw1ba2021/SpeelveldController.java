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
    private Button saveKnop;

    @FXML
    private Button loadKnop;

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
        vakkenVeld = new Vakken(23, 34);
        mannetje = new Mannetje(10, 10, vakkenVeld);
        spook = new Spook(0, 0);
        vak = new Vak();
        spoken = new Spoken(vak, vakkenVeld);
        vakkenSpeelveld = new Speelveld(vakkenVeld, mannetje, spoken);

        mannetjeView = new MannetjeView(mannetje);
        spokenView = new SpokenView(spoken);
        vakkenSpeelveldView = new SpeelveldView(vakkenSpeelveld, mannetje, spook, spoken, spokenView, mannetjeView, vakkenVeld);

        speelveld.getChildren().addAll(vakkenSpeelveldView, mannetjeView, spokenView);

        update();

        resetButton.setOnAction(this::reset);
        startKnop.setOnAction(this::start);
        StartMenuKnop.setOnAction(this::veranderSchermStartMenu);
        stopKnop.setOnAction(this::sluitGame);

        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
        StartMenuKnop.setFocusTraversable(false);
        stopKnop.setFocusTraversable(false);

        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
        startKnop.setFocusTraversable(false);
        saveKnop.setFocusTraversable(false);
        loadKnop.setFocusTraversable(false);

        System.out.println("init");

    }

    /**
     * deze methode update het speelveld
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
                    if (vakkenSpeelveld.ispositieMannetjeGevuld()) {
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
                    if (vakkenSpeelveld.ispositieMannetjeGevuld()) {
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
                    if (vakkenSpeelveld.ispositieMannetjeGevuld()) {
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
                    if (vakkenSpeelveld.ispositieMannetjeGevuld()) {
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
     * voor de reset knop de methode reset het speelveld en update de view
     */
    private void reset(ActionEvent e) {
        toetsGeluid();
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
     * voor de start knop de methode start het bewegen van de spoken en het
     * mannetje
     */
    private void start(ActionEvent e) {
        toetsGeluid();

        if (!start) {
            timer = new Timer(true);
            for (Spook s : spoken.getSpoken()) {
                BeweegSpook taskSpook = new BeweegSpook(s, this);
                timer.scheduleAtFixedRate(taskSpook, 0, 16);
            }

            BeweegMannetje taskMannetje = new BeweegMannetje(mannetje, this);
            timer.scheduleAtFixedRate(taskMannetje, 0, 200);

            start = true;

        }

    }

    /**
     * @param e is de actie/het event van de knop deze methode is de methode
     * voor de StartMenu knop deze methode zal de stage veranderen naar een
     * andere scene....
     *
     * gehaald van:...
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

            timer.cancel();
        } catch (IOException ex) {
        } catch (NullPointerException exe) {
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

    private void sluitGame(ActionEvent e) {
        System.exit(0);
    }

}
