/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import model.Mannetje;
import model.Speelveld;
import model.Spoken;
import model.Spook;
import model.StatusVak;
import model.Vak;

/**
 *
 * @author Gus Geurts
 */
public final class SpeelveldView extends Region {

    private final Speelveld speelveld;
    private final Mannetje mannetje;
    private final Spook spook;
    private final int teVullenVakken;
    private final Spoken spoken;
    private final SpokenView spokenView;
    private final MannetjeView mannetjeView;

    public SpeelveldView(Speelveld speelveld, Mannetje mannetje, Spook spook, Spoken spoken, SpokenView spokenView, MannetjeView mannetjeView) {
        this.speelveld = speelveld;
        this.mannetje = mannetje;
        this.spook = spook;
        this.spoken = spoken;
        this.spokenView = spokenView;
        this.mannetjeView = mannetjeView;

        teVullenVakken = speelveld.getRijen() * speelveld.getKolommen() - (2 * speelveld.getRijen() + 2 * (speelveld.getKolommen() - 2));

        update();
    }

    public void update() {

        getChildren().clear();

        maakSpeelveld();

        maakInDeMaakLijn();

        maakGevuldeLijn();

        raakInDeMaak();

    }

    public void maakSpeelveld() {
        int n = 0;
        int m = 0;
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                VakView vv = new VakView(vakken[i][j]);
                if (i == 0) {
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                if (i == speelveld.getRijen() - 1) {
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                if (j == 0) {
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                if (j == speelveld.getKolommen() - 1) {
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                vv.setTranslateX(20 * n);
                vv.setTranslateY(20 * m);
                n++;
                if (n == speelveld.getKolommen()) {
                    n = 0;
                }
                switch (vakken[i][j].getStatus()) {
                    case GEVULD:
                        vv.setId("idGevuld");
                        break;
                    case IN_DE_MAAK:
                        vv.setId("idInDeMaak");
                        break;
                    default:
                        vv.setId("idLeeg");
                        break;
                }
                getChildren().add(vv);
            }
            m++;
        }
    }

    public void resetVeld() {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 1; i < speelveld.getRijen() - 1; i++) {
            for (int j = 1; j < speelveld.getKolommen() - 1; j++) {
                vakken[i][j].setStatus(StatusVak.LEEG);
            }
        }
    }

    public void geraaktInPad() {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(StatusVak.LEEG);
                }
            }
        }
    }

    public void geraaktOpMannetje() {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(StatusVak.LEEG);
                }
            }
        }
    }

    public void maakGevuldeLijn() {
        Vak vakken[][] = speelveld.getVakken();
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.GEVULD)
                && mannetje.getX() != 0 && mannetje.getY() != 0) {
            resetGevaar();
            for (int i = 0; i < speelveld.getRijen(); i++) {
                for (int j = 0; j < speelveld.getKolommen(); j++) {
                    if ((vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK))) {
                        vakken[i][j].setStatus(StatusVak.GEVULD);

                    }
                }
            }
        }
    }

    public void maakInDeMaakLijn() {
        Vak vakken[][] = speelveld.getVakken();
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.LEEG)) {
            vakken[mannetje.getVakY()][mannetje.getVakX()].setStatus(StatusVak.IN_DE_MAAK);
        }
    }

    public void raakInDeMaak() {
        Vak vakken[][] = speelveld.getVakken();
        if (mannetje.getVakX() + 1 < speelveld.getKolommen()
                && mannetje.getVakX() - 1 >= 0
                && mannetje.getVakY() + 1 < speelveld.getRijen()
                && mannetje.getVakY() - 1 >= 0) {
            if (vakken[mannetje.getVakY()][mannetje.getVakX() + 1].getStatus().equals(StatusVak.IN_DE_MAAK)) {// rechts van mannetje 
                vakken[mannetje.getVakY()][mannetje.getVakX() + 1].setGevaar(true);
            }
            if (vakken[mannetje.getVakY()][mannetje.getVakX() - 1].getStatus().equals(StatusVak.IN_DE_MAAK)) { // links van mannetje
                vakken[mannetje.getVakY()][mannetje.getVakX() - 1].setGevaar(true);
            }
            if (vakken[mannetje.getVakY() + 1][mannetje.getVakX()].getStatus().equals(StatusVak.IN_DE_MAAK)) { // onder mannetje
                vakken[mannetje.getVakY() + 1][mannetje.getVakX()].setGevaar(true);
            }
            if (vakken[mannetje.getVakY() - 1][mannetje.getVakX()].getStatus().equals(StatusVak.IN_DE_MAAK)) { // boven mannetje
                vakken[mannetje.getVakY() - 1][mannetje.getVakX()].setGevaar(true);
            }
        }
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getGevaar()) {
            mannetje.isDood();
            geraaktInPad();
        }
    }

    public void resetGevaar() {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                vakken[i][j].setGevaar(false);
            }
        }
    }

    public int getProcentGevuld() {
        Vak vakken[][] = speelveld.getVakken();
        double n = 0;
        for (int i = 1; i < speelveld.getRijen() - 1; i++) {
            for (int j = 1; j < speelveld.getKolommen() - 1; j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.GEVULD)) {
                    n++;
                }
            }
        }
        return (int) ((n / teVullenVakken) * 100);
    }

    public boolean ispositieMannetjeGevuld() {
        Vak vakken[][] = speelveld.getVakken();
        return vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.GEVULD);
    }

    public void stilInGevuld() {
        if (ispositieMannetjeGevuld() && (mannetje.getVx() != 0 || mannetje.getVy() != 0)) {
            mannetje.setVx(0);
            mannetje.setVy(0);
        }
    }

    public void gameOver() {
        if (mannetje.getDood()) {
            doodNotificatie();
            resetVeld();
            mannetje.resetGame();
        }
    }

    public void gameGewonnen() {
        if (getProcentGevuld() >= 80) {
            winNotificatie();
            resetVeld();
            mannetje.resetGame();
        }
    }

    public void winNotificatie() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pac-Xon");
        alert.setContentText("Je hebt gewonnen!!!\nje vulde " + getProcentGevuld() + " % met nog " + mannetje.getLevens() + " levens over");
        alert.show();
    }

    public void doodNotificatie() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PacXon");
        alert.setContentText("du bist dood \nje vulde " + getProcentGevuld() + "%");
        alert.show();
    }

    public void spookRaaktGevuld() {
        ObservableList<Node> vakken = getChildren();
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
                        geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getY() - spook.getStraal() >= boundVak.getMaxY() - 3
                        && s.localToParent(Point2D.ZERO).getY() - spook.getStraal() <= boundVak.getMaxY() + 3
                        && s.localToParent(Point2D.ZERO).getX() >= boundVak.getMinX()
                        && s.localToParent(Point2D.ZERO).getX() <= boundVak.getMinX() + boundVak.getWidth()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVy(0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getX() - spook.getStraal() >= boundVak.getMaxX() - 3
                        && s.localToParent(Point2D.ZERO).getX() - spook.getStraal() <= boundVak.getMaxX() + 3
                        && s.localToParent(Point2D.ZERO).getY() >= boundVak.getMinY()
                        && s.localToParent(Point2D.ZERO).getY() <= boundVak.getMinY() + boundVak.getHeight()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVx(0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getX() + spook.getStraal() >= boundVak.getMinX() - 3
                        && s.localToParent(Point2D.ZERO).getX() + spook.getStraal() <= boundVak.getMinX() + 3
                        && s.localToParent(Point2D.ZERO).getY() >= boundVak.getMinY()
                        && s.localToParent(Point2D.ZERO).getY() <= boundVak.getMinY() + boundVak.getHeight()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVx(-0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        geraaktInPad();
                    }

                }

            }
            i++;
        }

    }

    public void mannetjeGeraaktDoorSpook() {
        ObservableList<Node> man = mannetjeView.getChildrenUnmodifiable();
        ObservableList<Node> spoken = spokenView.getChildrenUnmodifiable();

        for (Node m : man) {
            Bounds boundMannetje = m.localToScene(m.getBoundsInLocal());
            for (Node s : spoken) {
                Bounds boundSpoken = s.localToScene(s.getBoundsInLocal());
                if (boundMannetje.intersects(boundSpoken)) {
                    geraaktOpMannetje();
                    mannetje.isDood();
                }
            }
        }

    }
}
