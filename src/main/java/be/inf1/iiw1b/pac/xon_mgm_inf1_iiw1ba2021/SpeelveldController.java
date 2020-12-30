package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Mannetje;
import model.Speelveld;
import model.Spook;
import model.StatusVak;
import view.MannetjeView;
import view.SpeelveldView;
import view.SpookView;

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
    private Label labelMannetjeX;

    @FXML
    private Label labelMannetjeY;

    @FXML
    private Label labelLevens;

    @FXML
    private Label labelSpookX;

    @FXML
    private Label labelSpookY;

    @FXML
    private Label labelDood;

    @FXML
    private Label labelInvulProcent;

    private Mannetje mannetje;
    private MannetjeView mannetjeView;
    private Spook spook;
    private SpookView spookView;
    private Speelveld vakkenSpeelveld;
    private SpeelveldView vakkenSpeelveldView;

    @FXML
    void initialize() {

        mannetje = new Mannetje(10, 10);
        spook = new Spook(200, 200);
        vakkenSpeelveld = new Speelveld(23, 34);

        mannetjeView = new MannetjeView(mannetje);
        spookView = new SpookView(spook);     
        vakkenSpeelveldView = new SpeelveldView(vakkenSpeelveld, mannetje, spook);

        speelveld.getChildren().add(vakkenSpeelveldView);
        speelveld.getChildren().add(mannetjeView);
        speelveld.getChildren().add(spookView);

        update();

        speelveld.setOnKeyPressed(this::loopRond);
        resetButton.setOnAction(this::reset);

        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);

        BeweegSpook taskSpook = new BeweegSpook(spook, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(taskSpook, 0, 10);

    }

    public void update() {

        mannetjeView.update();
        spookView.update();
        vakkenSpeelveldView.update();

        labelMannetjeX.setText(mannetje.getX() + "");
        labelMannetjeY.setText(mannetje.getY() + "");
        labelSpookX.setText(spook.getX() + "");
        labelSpookY.setText(spook.getY() + "");
        labelLevens.setText(mannetje.getLevens() + "");
        labelDood.setText(mannetje.getDood() + "");
        labelInvulProcent.setText(vakkenSpeelveldView.getProcentGevuld() + "");

        geraakt();

        ogenSpook();

        gameOver();

    }

    private void loopRond(KeyEvent t) {
        switch (t.getCode()) {
            case RIGHT:
                mannetje.rechts();
                mannetje.setMaxXBorder();
                mannetjeView.getVormMannetje().setRotate(0);
                break;
            case LEFT:
                mannetje.links();
                mannetje.setMinXBorder();
                mannetjeView.getVormMannetje().setRotate(180);
                break;
            case UP:
                mannetje.boven();
                mannetje.setMinYBorder();
                mannetjeView.getVormMannetje().setRotate(270);
                break;
            case DOWN:
                mannetje.onder();
                mannetje.setMaxYBorder();
                mannetjeView.getVormMannetje().setRotate(90);
                break;
        }
        update();
    }

    private void reset(ActionEvent e) {
        mannetje.resetGame();
        vakkenSpeelveldView.reset(vakkenSpeelveld);
        update();
    }

    private void geraakt() {
        if (spookView.getVormSpook().intersects(spookView.getVormSpook().sceneToLocal(mannetjeView.getVormMannetje().localToScene(mannetjeView.getVormMannetje().getBoundsInLocal())))) {
            vakkenSpeelveldView.geraaktOpMannetje();
            mannetje.isDood();
        }
    }

    private void ogenSpook() {
        if (spook.getVx() < 0) {
            spookView.getVormSpook().setScaleX(-1);
        } else {
            spookView.getVormSpook().setScaleX(1);
        }
    }

    private void gameOver() {
        if (mannetje.getDood()) {
            vakkenSpeelveldView.reset(vakkenSpeelveld);
            mannetje.resetGame();
        }
    }
}
