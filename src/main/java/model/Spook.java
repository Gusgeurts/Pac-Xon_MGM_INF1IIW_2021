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
        vx = 0.5;
        vy = 0.5;
        straal = 10;

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
     * @return the straal
     */
    public int getStraal() {
        return straal;
    }

    public double verticaal() {
        y = y + vy;
        return y;
    }

    public double horizontaal() {
        x = x + vx;
        return x;
    }

    public void setVx(double vx) {
            this.vx = vx;
    }

    public void setVy(double vy) {
            this.vy = vy;
    }

    public void Tick() {
        verticaal();
        horizontaal();
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
