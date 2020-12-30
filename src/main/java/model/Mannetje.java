/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gus Geurts
 */
public class Mannetje {

    private double x;
    private double y;
    private int vx;
    private int vy;
    private boolean dood;
    private int levens;
    private int straal;

    public Mannetje(int x, int y) {
        this.x = x;
        this.y = y;
        straal = 10;
        vx = 0;
        vy = 0;
        levens = 5;
        dood = false;
    }

    /**
     * @return the x
     */
    public int getX() {
        return (int) x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return (int) y;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
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

    public int getLevens() {
        return levens;
    }

    public boolean getDood() {
        return dood;
    }

    public void resetGame() {
        x = 10;
        y = 10;
        levens = 5;
        dood = false;
    }

    public double verticaal() {
        y = y + (20*getVy());
        return y;
    }

    public double horizontaal() {
        x = x + (20*getVx());
        return x;
    }
    

    public void reset() {
        x = 10;
        y = 10;

    }

    public int setMaxXBorder() {
        if (x > 680 - getStraal()) {
            x = 680 - getStraal();
            return (int)x;
        }
        return (int)x;
    }

    public int setMinXBorder() {
        if (x < 10) {
            x = 10;
            return (int) x;
        }
        return (int) x;
    }

    public int setMaxYBorder() {
        if (y > 460 - getStraal()) {
            y = 460 - getStraal();
            return (int)y;
        }
        return (int)y;
    }

    public int setMinYBorder() {
        if (y < 10) {
            y = 10;
            return (int)y;
        }
        return (int)y;
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
