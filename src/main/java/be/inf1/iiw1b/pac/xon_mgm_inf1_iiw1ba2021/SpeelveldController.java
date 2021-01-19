package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Mannetje;
import model.Speelveld;
import model.Spoken;
import model.Spook;
import model.Vak;
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
    

    private Mannetje mannetje;
    private MannetjeView mannetjeView;
    private Spook spook;
    private Spoken spoken;
    private Vak vak;
    private Speelveld vakkenSpeelveld;
    private SpeelveldView vakkenSpeelveldView;
    private SpokenView spokenView;
    private boolean start = false;
    private Timer timer;

    @FXML
    void initialize() {

        vakkenSpeelveld = new Speelveld(23, 34);
        mannetje = new Mannetje(10, 10, vakkenSpeelveld);
        spook = new Spook(0, 0);
        vak = new Vak();
        spoken = new Spoken(2, vak, vakkenSpeelveld);

        mannetjeView = new MannetjeView(mannetje);
        vakkenSpeelveldView = new SpeelveldView(vakkenSpeelveld, mannetje);
        spokenView = new SpokenView(spoken);

        speelveld.getChildren().addAll(vakkenSpeelveldView, mannetjeView, spokenView);

        update();

        resetButton.setOnAction(this::reset);
        
        StartMenuKnop.setOnAction(this::veranderSchermStartMenu);
        
        
        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
        StartMenuKnop.setFocusTraversable(false);
        

        BeweegSpook taskSpook = new BeweegSpook(spook, this);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(taskSpook, 0, 10);

        BeweegMannetje taskMannetje = new BeweegMannetje(mannetje, this);
        t.scheduleAtFixedRate(taskMannetje, 0, 120);
        startButton.setOnAction(this::start);

        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
        startButton.setFocusTraversable(false);

    }

    public void update() {

        mannetjeView.update();
        spokenView.update();

        if (start) {

            speelveld.setOnKeyPressed(this::loopRond);

            mannetjeGeraaktDoorSpook();

            vakkenSpeelveldView.gameOver();
            vakkenSpeelveldView.stilInGevuld();
            spookRaaktGevuld();
        }
        vakkenSpeelveldView.update();

        labelLevens.setText(mannetje.getLevens() + "");
        labelInvulProcent.setText(vakkenSpeelveldView.getProcentGevuld() + " %");

    }

        gameOver();
        
        gameGewonnen();
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

    private void doodNotificatie() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pac-Xon");
        alert.setContentText("du bist dood");
        alert.setContentText("je vulde " + vakkenSpeelveldView.getProcentGevuld() + " %");
        alert.show();
    }
    private void gameGewonnen(){
        if (vakkenSpeelveldView.getProcentGevuld() >= 80){
            winNotificatie();
            vakkenSpeelveldView.reset(vakkenSpeelveld);
        }
        
    }
    private void winNotificatie(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pac-Xon");
        alert.setContentText("Je hebt gewonnen!!!\n je vulde " + vakkenSpeelveldView.getProcentGevuld() + " % met nog " + mannetje.getLevens() + " levens over");
        alert.show();                                        
    }

    public void veranderSchermStartMenu(ActionEvent e)  {
        try {
            Parent startMenuParent = null;
            startMenuParent = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
            Scene startMenuScene = new Scene(startMenuParent);
            Stage startMenuScherm = (Stage) ((Node) e.getSource()).getScene().getWindow();
            startMenuScherm.hide();
            startMenuScherm.setScene(startMenuScene);
            startMenuScherm.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
