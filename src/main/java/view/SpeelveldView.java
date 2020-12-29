/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import model.Mannetje;
import model.Speelveld;
import model.StatusVak;
import model.Vak;

/**
 *
 * @author Gus Geurts
 */
public class SpeelveldView extends Region {

    private Speelveld speelveld;
    private Vak vak;
    private StatusVak status;
    private Mannetje mannetje;

    public SpeelveldView(Speelveld speelveld, Mannetje mannetje) {
        this.speelveld = speelveld;
        this.mannetje = mannetje;
        update();
    }

    public void update() {

        int n = 0;
        int m = 0;
        getChildren().clear();
        Vak vakken[][] = speelveld.getVakken();
        for (int i = 0; i < speelveld.getRijen(); i++) {
            for (int j = 0; j < speelveld.getKolommen(); j++) {
                VakView vv = new VakView(vakken[i][j]);
                if (i == 0) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                if (i == 22) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                if (j == 0) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                if (j == 33) {
                    vakken[i][j].setStatus(status.GEVULD);
                }
                vv.setTranslateX(20 * n);
                vv.setTranslateY(20 * m);
                n++;
                if (n == speelveld.getKolommen()) {
                    n = 0;
                }
                getChildren().add(vv);
            }
            m++;

        }

        if (!vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].getStatus().equals(status.GEVULD)) {
            vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].setStatus(status.IN_DE_MAAK);
        }

        if (vakken[(mannetje.getY() - 10) / 20][(mannetje.getX() - 10) / 20].getStatus().equals(status.GEVULD)) {
            for (int i = 0; i < speelveld.getRijen(); i++) {
                for (int j = 0; j < speelveld.getKolommen(); j++) {
                    if ((vakken[i][j].getStatus().equals(status.IN_DE_MAAK))) {
                        vakken[i][j].setStatus(status.GEVULD);
                    }
                }
            }
        }
    }

}
