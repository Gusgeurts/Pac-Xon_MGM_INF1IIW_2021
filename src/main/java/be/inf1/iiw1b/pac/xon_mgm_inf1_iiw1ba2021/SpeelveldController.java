package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Mannetje;
import model.Speelveld;
import model.Spook;
import model.StatusVak;
import model.Vak;
import view.MannetjeView;
import view.SpeelveldView;
import view.SpookView;
import view.VakView;

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
    private StatusVak status;
    private VakView vakView;
    private Vak vak;

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

        BeweegMannetje taskMannetje = new BeweegMannetje(mannetje, this);
        t.scheduleAtFixedRate(taskMannetje, 0, 120);

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

        mannetjeGeraaktDoorSpook();

        ogenSpook();

        gameOver();

        stilInGevuld();

        spookRaaktGevuld();

    }

    private void loopRond(KeyEvent t) {
        switch (t.getCode()) {
            case RIGHT:
                mannetje.rechts();
                mannetje.setMaxXBorder();
                if (vakkenSpeelveldView.ispositieMannetjeGevuld()) {
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
                if (vakkenSpeelveldView.ispositieMannetjeGevuld()) {
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
                if (vakkenSpeelveldView.ispositieMannetjeGevuld()) {
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
                if (vakkenSpeelveldView.ispositieMannetjeGevuld()) {
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

    private void reset(ActionEvent e) {
        mannetje.resetGame();
        vakkenSpeelveldView.reset(vakkenSpeelveld);
        update();
    }

    private void mannetjeGeraaktDoorSpook() {
        ObservableList<Node> man = mannetjeView.getChildrenUnmodifiable();
        ObservableList<Node> spoken = spookView.getChildrenUnmodifiable();

        for (Node m : man) {
            Bounds boundMannetje = m.localToScene(m.getBoundsInLocal());
            for (Node s : spoken) {
                Bounds boundSpoken = s.localToScene(s.getBoundsInLocal());
                if (boundMannetje.intersects(boundSpoken)) {
                    vakkenSpeelveldView.geraaktOpMannetje();
                    mannetje.isDood();
                }
            }
        }

    }

    private void spookRaaktGevuld() {
        ObservableList<Node> vakken = vakkenSpeelveldView.getChildrenUnmodifiable();
        ObservableList<Node> spoken = spookView.getChildrenUnmodifiable();

        int i = 0;

        for (Node v : vakken) {
            Bounds boundVak = v.localToScene(v.getBoundsInLocal());
            for (Node s : spoken) {
                if (spook.getY() + spook.getStraal() >= boundVak.getMinY()
                        && spook.getY() + spook.getStraal() <= boundVak.getMinY()
                        && spook.getX() >= boundVak.getMinX()
                        && spook.getX() <= boundVak.getMinX() + boundVak.getWidth()
                        && v.getId().equals("idGevuld")) {
                    spook.setVy();
                }
                if (spook.getY() - spook.getStraal() >= boundVak.getMaxY()
                        && spook.getY() + spook.getStraal() <= boundVak.getMaxY()
                        && spook.getX() >= boundVak.getMinX()
                        && spook.getX() <= boundVak.getMinX() + boundVak.getWidth()
                        && v.getId().equals("idGevuld")) {
                    spook.setVy();
                }
                if (spook.getX() - spook.getStraal() >= boundVak.getMaxX()
                        && spook.getX() - spook.getStraal() <= boundVak.getMaxX()
                        && spook.getY() >= boundVak.getMinY()
                        && spook.getY() <= boundVak.getMinY() + boundVak.getHeight()
                        && v.getId().equals("idGevuld")) {
                    spook.getVx();
                }
                if (spook.getX() + spook.getStraal() >= boundVak.getMinX()
                        && spook.getX() - spook.getStraal() <= boundVak.getMinX()
                        && spook.getY() >= boundVak.getMinY()
                        && spook.getY() <= boundVak.getMinY() + boundVak.getHeight()
                        && v.getId().equals("idGevuld")) {
                    spook.getVx();
                }
            }
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

    public void stilInGevuld() {
        if (vakkenSpeelveldView.ispositieMannetjeGevuld() && (mannetje.getVx() != 0 || mannetje.getVy() != 0)) {
            mannetje.setVx(0);
            mannetje.setVy(0);
        }
    }
}
