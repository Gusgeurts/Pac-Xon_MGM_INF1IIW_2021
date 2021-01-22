/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class Mannetje {

    private double x;
    private double y;
    private int vx;
    private int vy;
    private boolean dood;
    private int levens;
    private final int straal;
    private final Vakken vakkenVeld;

    /**
     *
     * @param x is de x-coördinaat van het mannetje
     * @param y is de y-coördinaat van het mannetje
     * @param vakkenSpeelveld is het aangemaakt speelveld Deze methode geeft
     * alle variabelen een begin/start waarde
     */
    public Mannetje(int x, int y, Vakken vakkenVeld) {
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        dood = false;
        levens = 5;
        straal = 10;
        this.vakkenVeld = vakkenVeld;
    }

    /**
     * @return x-vak-coördinaat van het mannetje
     */
    public int getVakX() {
        return (int) (x - 10) / 20;
    }

    /**
     * @return y-vak-coördinaat van het mannetje
     */
    public int getVakY() {
        return (int) (y - 10) / 20;
    }

    /**
     * @return x-coördinaat van het mannetje
     */
    public int getX() {
        return (int) x;
    }

    /**
     * @return y-coördinaat van het mannetje
     */
    public int getY() {
        return (int) y;
    }

    /**
     * @param x is de x-coördinaat van het mannetje deze methode geeft
     * x-coördinaat van het mannetje een nieuwe waarde
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y is de y-coördinaat van het mannetje deze methode geeft
     * y-coördinaat van het mannetje een nieuwe waarde
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * deze methode veranderd het aantal levens van het mannetje als hij dood
     * gaat en als hij geen levens meer overheeft wordt dood true/is het
     * mannetje dood
     */
    public void isDood() {
        speelDoodGeluid();
        if (levens != 0) {
            levens--;
            reset();
            if (levens == 0) {
                dood = true;

            }
        }
    }

    /**
     * @return geeft het aantal levens terug
     */
    public int getLevens() {
        return levens;
    }

    /**
     * @return Geeft terug of het mannetje dood/true of levend/false is
     */
    public boolean getDood() {
        return dood;
    }

    /**
     * Deze methode reset de game het mannetje wordt terug op begin positie
     * gezet aantal levens terug op 5 en mannetje terug levend/ dood wordt flase
     */
    public void resetGame() {
        x = 10;
        y = 10;
        levens = 5;
        dood = false;
    }

    /**
     * @return Deze methode laat het mannetje 1 vak verticaal verplaatsen met
     * een bepaalde snelheid
     */
    public double verticaal() {
        y = y + (20 * getVy());
        return y;
    }

    /**
     *
     * @return Deze methode laat het mannetje 1 vak horizontaal verplaatsen met
     * een bepaalde snelheid
     */
    public double horizontaal() {
        x = x + (20 * getVx());
        return x;
    }

    /**
     * Deze methode reset de positie van het mannetje terug naar rechtsboven/
     * beginpositie
     */
    public void reset() {
        x = 10;
        y = 10;

    }

    /**
     *
     * @return Deze methode zorgt ervoor dat het mannetje niet buiten de border
     * zal gaan aan de linkerkant van het speelveld in de x-richting
     */
    public int setMaxXBorder() {
        if (x > (20 * vakkenVeld.getKolommen()) - straal) {
            x = (20 * vakkenVeld.getKolommen()) - straal;
            return (int) x;
        }
        return (int) x;
    }

    /**
     *
     * @return Deze methode zorgt ervoor dat het mannetje niet buiten de border
     * zal gaan aan de rechterkant van het speelveld in de x-richting
     */
    public int setMinXBorder() {
        if (x < 10) {
            x = 10;
            return (int) x;
        }
        return (int) x;
    }

    /**
     *
     * @return Deze methode zorgt ervoor dat het mannetje niet buiten de border
     * zal gaan aan de onderkant van het speelveld in de y-richting
     */
    public int setMaxYBorder() {
        if (y > (20 * vakkenVeld.getRijen()) - straal) {
            y = (20 * vakkenVeld.getRijen()) - straal;
            return (int) y;
        }
        return (int) y;
    }

    /**
     *
     * @return Deze methode zorgt ervoor dat het mannetje niet buiten de border
     * zal gaan aan de bovenkant van het speelveld in de y-richting
     */
    public int setMinYBorder() {
        if (y < 10) {
            y = 10;
            return (int) y;
        }
        return (int) y;
    }

    /**
     * @return deze methode geeft de straal terug van het mannetje
     */
    public int getStraal() {
        return straal;
    }

    /**
     * Deze methode verplaatst het mannetje 20 pixels of 1 vak naar links
     */
    public void links() {
        x = x - 20;
    }

    /**
     * Deze methode verplaatst het mannetje 20 pixels of 1 vak naar rechts
     */
    public void rechts() {
        x = x + 20;
    }

    /**
     * Deze methode verplaatst het mannetje 20 pixels of 1 vak naar boven
     */
    public void boven() {
        y = y - 20;

    }

    /**
     * Deze methode verplaatst het mannetje 20 pixels of 1 vak naar onder
     */
    public void onder() {
        y = y + 20;

    }

    /**
     * Deze methode gaat de volgende methodes checken en constant uitvoeren
     */
    public void Tick() {
        verticaal();
        horizontaal();
        setMaxXBorder();
        setMinXBorder();
        setMaxYBorder();
        setMinYBorder();
    }

    /**
     * @param vx is de snelheid in de x-richting van het mannetje Deze methode
     * geeft de snelhied in de x-richting een nieuwe waarde
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     * @param vy is de snelhied in de y-richting van het mannetje Deze methode
     * geeft de snelhied in de y-richting een nieuwe waarde
     */
    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * @return geeft de waarde van de snelhied in de x-richting terug
     */
    public int getVx() {
        return vx;
    }

    /**
     * @return geeft de waarde van de snelhied in de y-richting terug
     */
    public int getVy() {
        return vy;
    }

    MediaPlayer mediaPlayer;

    public void speelDoodGeluid() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("Meurice_da_vind_ik.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

    }

}
