package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Mannetje;
import model.Speelveld;
import model.Spoken;
import model.Spook;
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
    private Button startButton;

    @FXML
    private Label labelMannetjeX;

    @FXML
    private Label labelMannetjeY;

    @FXML
    private Label labelLevens;

    @FXML
    private Label labelDood;

    @FXML
    private Label labelInvulProcent;

    private Mannetje mannetje;
    private MannetjeView mannetjeView;
    private Spook spook;
    private Spoken spoken;
    private Speelveld vakkenSpeelveld;
    private SpeelveldView vakkenSpeelveldView;
    private SpokenView spokenView;
    private boolean start = false;
    private Timer timer;

    @FXML
    void initialize() {

        vakkenSpeelveld = new Speelveld(23, 34);
        mannetje = new Mannetje(10, 10, vakkenSpeelveld);
        spook = new Spook(200, 200);
        spoken = new Spoken(2, vakkenSpeelveld);

        mannetjeView = new MannetjeView(mannetje);
        vakkenSpeelveldView = new SpeelveldView(vakkenSpeelveld, mannetje, spook);
        spokenView = new SpokenView(spoken);

        speelveld.getChildren().addAll(vakkenSpeelveldView, mannetjeView, spokenView);

        update();

        resetButton.setOnAction(this::reset);
        startButton.setOnAction(this::start);

        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
        startButton.setFocusTraversable(false);

    }

    public void update() {

        //ogenSpook();
        mannetjeView.update();
        spokenView.update();

        if (start) {

            speelveld.setOnKeyPressed(this::loopRond);

            mannetjeGeraaktDoorSpook();

            gameOver();
            stilInGevuld();
            spookRaaktGevuld();
        }
        vakkenSpeelveldView.update();

        labelMannetjeX.setText(mannetje.getX() + "");
        labelMannetjeY.setText(mannetje.getY() + "");
        labelLevens.setText(mannetje.getLevens() + "");
        labelDood.setText(mannetje.getDood() + "");
        labelInvulProcent.setText(vakkenSpeelveldView.getProcentGevuld() + "");

    }

    private void loopRond(KeyEvent t) {
        if (start) {

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
    }

    private void reset(ActionEvent e) {
        if (start) {
            mannetje.resetGame();
            vakkenSpeelveldView.resetVeld();
            mannetjeView.getVormMannetje().setRotate(0);
            timer.cancel();

            update();
        }
        start = false;

    }

    private void start(ActionEvent e) {

        if (!start) {
            timer = new Timer(true);
            for (Spook s : spoken.getSpoken()) {
                BeweegSpook taskSpook = new BeweegSpook(s, this);
                timer.scheduleAtFixedRate(taskSpook, 0, 14);
            }

            BeweegMannetje taskMannetje = new BeweegMannetje(mannetje, this);
            timer.scheduleAtFixedRate(taskMannetje, 0, 120);

            start = true;

        }

    }

    private void mannetjeGeraaktDoorSpook() {
        ObservableList<Node> man = mannetjeView.getChildrenUnmodifiable();
        ObservableList<Node> spoken = spokenView.getChildrenUnmodifiable();

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
        ObservableList<Node> spoken = spokenView.getChildrenUnmodifiable();
        ArrayList<Spook> sp = this.spoken.getSpoken();
        int i = 0;

        for (Node s : spoken) {

            Bounds boundSpook = s.localToParent(s.getBoundsInLocal());

            for (Node v : vakken) {
                Bounds boundVak = v.localToParent(v.getBoundsInLocal());
                if (s.localToParent(Point2D.ZERO).getY() + spook.getStraal() >= boundVak.getMinY() - 3
                        && s.localToParent(Point2D.ZERO).getY() + spook.getStraal() <= boundVak.getMinY() + 3
                        && s.localToParent(Point2D.ZERO).getX() >= boundVak.getMinX()
                        && s.localToParent(Point2D.ZERO).getX() <= boundVak.getMinX() + boundVak.getWidth()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVy(-0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveldView.geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getY() - spook.getStraal() >= boundVak.getMaxY() - 3
                        && s.localToParent(Point2D.ZERO).getY() - spook.getStraal() <= boundVak.getMaxY() + 3
                        && s.localToParent(Point2D.ZERO).getX() >= boundVak.getMinX()
                        && s.localToParent(Point2D.ZERO).getX() <= boundVak.getMinX() + boundVak.getWidth()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVy(0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveldView.geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getX() - spook.getStraal() >= boundVak.getMaxX() - 3
                        && s.localToParent(Point2D.ZERO).getX() - spook.getStraal() <= boundVak.getMaxX() + 3
                        && s.localToParent(Point2D.ZERO).getY() >= boundVak.getMinY()
                        && s.localToParent(Point2D.ZERO).getY() <= boundVak.getMinY() + boundVak.getHeight()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVx(0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveldView.geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getX() + spook.getStraal() >= boundVak.getMinX() - 3
                        && s.localToParent(Point2D.ZERO).getX() + spook.getStraal() <= boundVak.getMinX() + 3
                        && s.localToParent(Point2D.ZERO).getY() >= boundVak.getMinY()
                        && s.localToParent(Point2D.ZERO).getY() <= boundVak.getMinY() + boundVak.getHeight()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVx(-0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveldView.geraaktInPad();
                    }

                }

            }
            i++;
        }

    }

    private void ogenSpook() {
        ObservableList<Node> spokenLijst = spokenView.getChildrenUnmodifiable();

        int i = 0;
        for (Spook s : spoken.getSpoken()) {
            if (s.getVx() > 0) {
                spokenLijst.get(i).setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            } else {
                spokenLijst.get(i).setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            }
            i++;
        }

    }

    private void gameOver() {
        if (mannetje.getDood()) {
            doodNotificatie();
            vakkenSpeelveldView.resetVeld();
            mannetje.resetGame();
        }
    }

    public void stilInGevuld() {
        if (vakkenSpeelveldView.ispositieMannetjeGevuld() && (mannetje.getVx() != 0 || mannetje.getVy() != 0)) {
            mannetje.setVx(0);
            mannetje.setVy(0);
        }
    }

    private void doodNotificatie() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PacXon");
        alert.setContentText("du bist dood");
        alert.setContentText("je vulde " + vakkenSpeelveldView.getProcentGevuld() + " %");
        alert.show();
    }
}
