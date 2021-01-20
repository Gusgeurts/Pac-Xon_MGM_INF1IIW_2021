package model;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class Spook {

    private double x;
    private double y;

    private double vx;
    private double vy;

    private final int straal;

    /**
     * @param x is de x-coördinaat van het spook
     * @param y is de y-coördinaat van het spook
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Spook(double x, double y) {
        this.x = x;
        this.y = y;
        vx = 0.5;
        vy = 0.5;
        straal = 10;

    }

    /**
     * @return geeft de x-coördinaat van het vak waarin het spook zich bevindt terug
     */
    public int getVakX() {
        return (int) (x - 10) / 20;
    }

    /**
     * @return geeft de x-coördinaat van het vak waarin het spook zich bevindt terug
     */
    public int getVakY() {
        return (int) (y - 10) / 20;
    }

    /**
     * @return geeft de x-coördinaat van het spook terug
     */
    public int getX() {
        return (int) x;
    }

    /**
     * @return geeft de y-coördinaat van het spook terug
     */
    public int getY() {
        return (int) y;
    }

    /**
     * @return geeft de straal van het spook terug
     */
    public int getStraal() {
        return straal;
    }

    /**
     * @return Deze methode laat het spook 1 vak verticaal verplaatsen met
     * een bepaalde snelheid
     */
    public double verticaal() {
        y = y + vy;
        return y;
    }

    /**
     * @return Deze methode laat het spook 1 vak horizontaal verplaatsen met
     * een bepaalde snelheid
     */
    public double horizontaal() {
        x = x + vx;
        return x;
    }

    /**
     * @param vx is de snelheid in de x-richting van het spook
     * Deze methode geeft de snelhied in de x-richting een nieuwe waarde 
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @param vy is de snelheid in de y-richting van het spook
     * Deze methode geeft de snelhied in de y-richting een nieuwe waarde
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * Deze methode gaat de volgende methodes checken en constant uitvoeren
     */
    public void Tick() {
        verticaal();
        horizontaal();
    }

    /**
     * @return geeft de waarde van de snelhied in de x-richting terug
     */
    public double getVx() {
        return vx;
    }

    /**
     * @return geeft de waarde van de snelhied in de y-richting terug
     */
    public double getVy() {
        return vy;
    }
}
