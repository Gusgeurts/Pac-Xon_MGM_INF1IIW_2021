/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Gus Geurts
 */
public class Spook {

    private double x;
    private double y;

    private double vx;
    private double vy;

    private int straal;

    public Spook(int x, int y) {
        this.x = x;
        this.y = y;
        Random rndx = new Random();
        Random rndy = new Random();
        vx = rndx.nextDouble() * (1 - (-1)) + -1;
        vy = rndy.nextDouble() * (1 - (-1)) + -1;
        straal = 10;

    }

    /**
     * @return the x
     */
    public double getX() {
        return Math.round(x);
    }

    /**
     * @return the y
     */
    public double getY() {
        return Math.round(y);
    }

    /**
     * @return the straal
     */
    public int getStraal() {
        return straal;
    }

    public double verticaal() {
        y = y - getVy();
        return y;
    }

    public double horizontaal() {
        x = x - getVx();
        return x;
    }

    public void setMaxX() {
        if (x > 680 - getStraal()) {
            vx = getVx() * -1;
        }
    }

    public void setMinX() {
        if (x < getStraal()) {
            vx = getVx() * -1;
        }
    }

    public void setMaxY() {
        if (y > 460 - getStraal()) {
            vy = getVy() * -1;
        }
    }

    public void setMinY() {
        if (y < getStraal()) {
            vy = getVy() * -1;
        }
    }

    public void Tick() {
        verticaal();
        horizontaal();
        setMaxX();
        setMaxY();
        setMinX();
        setMinY();
    }

    /**
     * @return the vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * @return the vy
     */
    public double getVy() {
        return vy;
    }
}
