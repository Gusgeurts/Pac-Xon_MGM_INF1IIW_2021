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
public class Spook {

    private int x;
    private int y;

    private int vx;
    private int vy;

    private int straal;

    public Spook(int x, int y) {
        this.x = x;
        this.y = y;

        vx = 30;
        vy = 30;
        straal = 8;

    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the straal
     */
    public int getStraal() {
        return straal;
    }

    public int omhoog() {
        y = y - vy;
        return y;
    }

    public int omlaag() {
        y = y + vy;
        return y;
    }

    public int links() {
        x = x - vx;
        return x;
    }

    public int rechts() {
        x = x + vx;
        return x;
    }

    public int setMaxX() {
        if (x > 600 - getStraal()) {
            x = 600 - getStraal();
            return x;
        }
        return x;
    }

    public int setMinX() {
        if (x < getStraal()) {
            x = getStraal();
            return x;
        }
        return x;
    }

    public int setMaxY() {
        if (y > 500 - getStraal()) {
            y = 500 - getStraal();
            return y;
        }
        return y;
    }

    public int setMinY() {
        if (y < getStraal()) {
            y = getStraal();
            return y;
        }
        return y;
    }

}
