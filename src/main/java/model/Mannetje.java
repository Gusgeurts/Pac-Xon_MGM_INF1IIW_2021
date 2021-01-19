/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gus Geurts/Michiel Meurice/Michiel Vanherreweghe
 */
public class Mannetje {

    private double x;
    private double y;
    private int vx;
    private int vy;
    private boolean dood;
    private int levens;
    private final int straal;
    private final Speelveld vakkenSpeelveld;

    public Mannetje(int x, int y, Speelveld vakkenSpeelveld) {
        this.vakkenSpeelveld = vakkenSpeelveld;
        this.x = x;
        this.y = y;
        straal = 10;
        vx = 0;
        vy = 0;
        levens = 5;
        dood = false;
    }

    /**
     * @param (int) x is de x-coördinaat van het mannetje
     * @return x-vak-coördinaat van het mannetje
     */
    public int getVakX() {
        return (int) (x - 10) / 20;
    }

    /**
     * @param (int) y is de y-coördinaat van het mannetje
     * @return y-vak-coördinaat van het mannetje
     */
    public int getVakY() {
        return (int) (y - 10) / 20;
    }

    /**
     * @param (int) x is de x-coördinaat van het mannetje
     * @return x-coördinaat van het mannetje
     */
    public int getX() {
        return (int) x;
    }

    /**
     * @param (int) y is de y-coördinaat van het mannetje
     * @return y-coördinaat van het mannetje
     */
    public int getY() {
        return (int) y;
    }

    /**
     * @param x is de x-coördinaat van het mannetje geeft x-coördinaat van het
     * mannetje nieuwe waarde
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y is de y-coördinaat van het mannetje geeft y-coördinaat van het
     * mannetje nieuwe waarde
     */
    public void setY(int y) {
        this.y = y;
    }

    public void isDood() {
        if (levens != 0) {
            levens--;
            reset();
            if (levens == 0) {
                dood = true;
            }
        }
    }

    /**
     * @param (int)levens is het aantal levens
     * @return geeft het aantal levens terug
     */
    public int getLevens() {
        return levens;
    }

    /**
     * @param (boolean) dood is de status van het mannetje
     * levend/dood(true/false)
     * @return geeft terug of het mannetje dood/true of levend/false is
     */
    public boolean getDood() {
        return dood;
    }

    /**
     * reset de game: mannetje terug op begin positie, aantal levens terug op 5
     * en mannetje terug levend/flase
     */
    public void resetGame() {
        x = 10;
        y = 10;
        levens = 5;
        dood = false;
    }

    public double verticaal() {
        y = y + (20 * getVy());
        return y;
    }

    public double horizontaal() {
        x = x + (20 * getVx());
        return x;
    }

    public void reset() {
        x = 10;
        y = 10;

    }

    public int setMaxXBorder() {
        if (x > (20 * vakkenSpeelveld.getKolommen()) - straal) {
            x = (20 * vakkenSpeelveld.getKolommen()) - straal;
            return (int) x;
        }
        return (int) x;
    }

    public int setMinXBorder() {
        if (x < 10) {
            x = 10;
            return (int) x;
        }
        return (int) x;
    }

    public int setMaxYBorder() {
        if (y > (20 * vakkenSpeelveld.getRijen()) - straal) {
            y = (20 * vakkenSpeelveld.getRijen()) - straal;
            return (int) y;
        }
        return (int) y;
    }

    public int setMinYBorder() {
        if (y < 10) {
            y = 10;
            return (int) y;
        }
        return (int) y;
    }

    public int getStraal() {
        return straal;
    }

    public void links() {
        x = x - 20;
    }

    public void rechts() {
        x = x + 20;

    }

    public void boven() {
        y = y - 20;

    }

    public void onder() {
        y = y + 20;

    }

    public void Tick() {
        verticaal();
        horizontaal();
        setMaxXBorder();
        setMinXBorder();
        setMaxYBorder();
        setMinYBorder();
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * @return the vx
     */
    public int getVx() {
        return vx;
    }

    /**
     * @return the vy
     */
    public int getVy() {
        return vy;
    }
}
