/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import model.Mannetje;
import model.Speelveld;
import model.Spook;
import model.StatusVak;
import model.Vak;

/**
 *
 * @author Gus Geurts
 */
public class SpeelveldView extends Region {

    private Speelveld speelveld;
    private StatusVak status;
    private Mannetje mannetje;
    private Spook spook;
    private int teVullenVakken;

    public SpeelveldView(Speelveld speelveld, Mannetje mannetje, Spook spook) {
        this.speelveld = speelveld;
        this.mannetje = mannetje;
        this.spook = spook;

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
                    vakken[i][j].setStatus(status.GEVULD);
                }
                if (i == speelveld.getRijen() - 1) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                if (j == 0) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                if (j == speelveld.getKolommen() - 1) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                vv.setTranslateX(20 * n);
                vv.setTranslateY(20 * m);
                n++;
                if (n == speelveld.getKolommen()) {
                    n = 0;
                }
                if (vakken[i][j].getStatus().equals(status.GEVULD)) {
                    vv.setId("idGevuld");
                } else if (vakken[i][j].getStatus().equals(status.IN_DE_MAAK)) {
                    vv.setId("idInDeMaak");
                } else {
                    vv.setId("idLeeg");
                }
                getChildren().add(vv);
            }
            m++;
        }
    }

    public void reset(Speelveld speelveld) {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                if (i != 0) {
                    vakken[i][j].setStatus(status.LEEG);
                }
                if (i != 22) {
                    vakken[i][j].setStatus(status.LEEG);
                }
                if (j != 0) {
                    vakken[i][j].setStatus(status.LEEG);
                }
                if (j != 33) {
                    vakken[i][j].setStatus(status.LEEG);
                }
            }
        }
    }

    public void geraaktInPad() {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(status.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(status.LEEG);
                }

            }
        }
    }

    public void geraaktOpMannetje() {
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(status.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(status.LEEG);
                }
            }
        }
    }

    public void maakGevuldeLijn() {
        Vak vakken[][] = speelveld.getVakken();
        if (vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].getStatus().equals(status.GEVULD)
                && (mannetje.getX() - 10 / 20) != 0 && (mannetje.getY() - 10 / 20) != 0) {
            resetGevaar();
            for (int i = 0; i < speelveld.getRijen(); i++) {
                for (int j = 0; j < speelveld.getKolommen(); j++) {
                    if ((vakken[i][j].getStatus().equals(status.IN_DE_MAAK))) {
                        vakken[i][j].setStatus(status.GEVULD);

                    }
                }
            }
        }
    }

    public void maakInDeMaakLijn() {
        Vak vakken[][] = speelveld.getVakken();
        if (vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].getStatus().equals(status.LEEG)) {
            vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].setStatus(status.IN_DE_MAAK);
        }
    }

    public void raakInDeMaak() {
        Vak vakken[][] = speelveld.getVakken();
        if (((mannetje.getX() - 10) / 20) + 1 < speelveld.getKolommen()
                && ((mannetje.getX() - 10) / 20) - 1 >= 0
                && ((mannetje.getY() - 10) / 20) + 1 < speelveld.getRijen()
                && ((mannetje.getY() - 10) / 20) - 1 >= 0) {
            if (vakken[(mannetje.getY() - 10) / 20][((mannetje.getX() - 10) / 20) + 1].getStatus().equals(status.IN_DE_MAAK)) {// rechts van mannetje 
                vakken[(mannetje.getY() - 10) / 20][((mannetje.getX() - 10) / 20) + 1].setGevaar(true);
            }
            if (vakken[(mannetje.getY() - 10) / 20][((mannetje.getX() - 10) / 20) - 1].getStatus().equals(status.IN_DE_MAAK)) { // links van mannetje
                vakken[(mannetje.getY() - 10) / 20][((mannetje.getX() - 10) / 20) - 1].setGevaar(true);
            }
            if (vakken[((mannetje.getY() - 10) / 20) + 1][((mannetje.getX() - 10) / 20)].getStatus().equals(status.IN_DE_MAAK)) { // onder mannetje
                vakken[((mannetje.getY() - 10) / 20) + 1][((mannetje.getX() - 10) / 20)].setGevaar(true);
            }
            if (vakken[((mannetje.getY() - 10) / 20) - 1][((mannetje.getX() - 10) / 20)].getStatus().equals(status.IN_DE_MAAK)) { // boven mannetje
                vakken[((mannetje.getY() - 10) / 20) - 1][((mannetje.getX() - 10) / 20)].setGevaar(true);
            }
        }
        if (vakken[(mannetje.getY() - 10) / 20][((mannetje.getX() - 10) / 20)].getGevaar()) {
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
                if (vakken[i][j].getStatus().equals(status.GEVULD)) {
                    n++;
                }
            }
        }
        return (int) ((n / teVullenVakken) * 100);
    }

    public boolean ispositieMannetjeGevuld() {
        Vak vakken[][] = speelveld.getVakken();
        if (vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].getStatus().equals(status.GEVULD)) {
            return true;
        }
        return false;
    }
}
