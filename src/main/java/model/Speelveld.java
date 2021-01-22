package model;

import javafx.scene.control.Alert;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class Speelveld {

    private final Mannetje mannetje;
    private final int teVullenVakken;
    private final Vakken vakkenVeld;
    private final Spoken spoken;

    /**
     * @param rijen is het aantal rijen van het speelveld
     * @param kolommen is het aantal kolommen van het speelveld Deze methode
     * geeft alle variabelen een begin/start waarde
     */
    public Speelveld(Vakken vakkenVeld, Mannetje mannetje, Spoken spoken) {
        this.mannetje = mannetje;
        this.vakkenVeld = vakkenVeld;
        this.spoken = spoken;
        teVullenVakken = vakkenVeld.getRijen() * vakkenVeld.getKolommen() - (2 * vakkenVeld.getRijen() + 2 * (vakkenVeld.getKolommen() - 2));

    }

    public void resetVeld() {
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 1; i < vakkenVeld.getRijen() - 1; i++) {
            for (int j = 1; j < vakkenVeld.getKolommen() - 1; j++) {
                vakken[i][j].setStatus(StatusVak.LEEG);
            }
        }
    }

    public void geraaktInPad() {
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(StatusVak.LEEG);
                }
            }
        }
    }

    public void geraaktOpMannetje() {
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(StatusVak.LEEG);
                }
            }
        }
    }

    public void maakGevuldeLijn() {
        Vak vakken[][] = vakkenVeld.getVakken();
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.GEVULD)
                && mannetje.getX() != 0 && mannetje.getY() != 0) {
            resetGevaar();
            for (int i = 0; i < vakkenVeld.getRijen(); i++) {
                for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                    if ((vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK))) {
                        vakken[i][j].setStatus(StatusVak.GEVULD);

                    }
                }
            }
        }
    }

    public void maakInDeMaakLijn() {
        Vak vakken[][] = vakkenVeld.getVakken();
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.LEEG)) {
            vakken[mannetje.getVakY()][mannetje.getVakX()].setStatus(StatusVak.IN_DE_MAAK);
        }
    }

    public void raakInDeMaak() {
        Vak vakken[][] = vakkenVeld.getVakken();
        if (mannetje.getVakX() + 1 < vakkenVeld.getKolommen()
                && mannetje.getVakX() - 1 >= 0
                && mannetje.getVakY() + 1 < vakkenVeld.getRijen()
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
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                vakken[i][j].setGevaar(false);
            }
        }
    }

    public int getProcentGevuld() {
        Vak vakken[][] = vakkenVeld.getVakken();
        double n = 0;
        for (int i = 1; i < vakkenVeld.getRijen() - 1; i++) {
            for (int j = 1; j < vakkenVeld.getKolommen() - 1; j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.GEVULD)) {
                    n++;
                }
            }
        }
        return (int) ((n / teVullenVakken) * 100);
    }

    public boolean ispositieMannetjeGevuld() {
        Vak vakken[][] = vakkenVeld.getVakken();
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

    public void updateSpeelveld() {

        gameGewonnen();
        gameOver();
        stilInGevuld();
        maakInDeMaakLijn();
        maakGevuldeLijn();
        raakInDeMaak();

        //int j
        //int vorigeJ = 110;
        //if (j > vorigeJ) {
        //    kleurHetVeld();
        //    vorigeJ = j;
    }

    //}
    /*
    public void kleurHetVeld() {
        //Vak[][] vakken = vakkenVeld.getVakken();
        //for (int i = 0; i < vakkenVeld.getRijen(); i++) {
        //for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
        //               if (vakken[i][j].getStatus() == StatusVak.LEEG) {
        if (spookChecker(mannetje.getVakX(), mannetje.getVakY())) {
            kleurenVeld(mannetje.getVakX(), mannetje.getVakY());
        }
        //             }
        //}
        //}

    }

    public void kleurenVeld(int x, int y) {
        Vak[][] vakken = vakkenVeld.getVakken();
        for (int i = max(0, x - 1); i <= min(vakkenVeld.getRijen() - 1, x + 1); i++) {
            for (int j = max(0, y - 1); j <= min(vakkenVeld.getKolommen() - 1, y + 1); j++) {
                if (vakken[i][j].getStatus() == StatusVak.LEEG) {
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                    kleurenVeld(i, j);
                }
            }
        }
    }

    public boolean spookChecker(int x, int y) {
        Vak[][] vakken = vakkenVeld.getVakken();
        boolean geenSpook = true;
        ArrayList<Spook> s = spoken.getSpoken();
        for (int t = 0; t <= s.size() - 1; t++) {
            for (int i = max(0, x - 1); i <= min(vakkenVeld.getRijen() - 1, x + 1); i++) {
                if (i == round(spoken.getSpoken().get(t).getVakX())) {
                    for (int j = max(0, y - 1); j <= min(vakkenVeld.getKolommen() - 1, y + 1); j++) {
                        if (j == round(spoken.getSpoken().get(t).getVakY())) {
                            geenSpook = false;
                        }
                    }
                }
            }
            if (!geenSpook) {
                return geenSpook;
            }
        }

        if (geenSpook) {
            for (int i = max(0, x - 1); i <= min(vakkenVeld.getRijen() - 1, x + 1); i++) {
                for (int j = max(0, y - 1); j <= min(vakkenVeld.getRijen() - 1, y + 1); j++) {
                    if (vakken[x][y].getStatus() == StatusVak.LEEG) {
                        geenSpook = spookChecker(i, j);
                    }
                }
            }

        }
        return geenSpook;
    }
     */
}
