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

    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean dood;
    private int levens;
    private int straal;

    public Mannetje(int x, int y) {
        this.x = x;
        this.y = y;
        straal = 10;
        dx = 0;
        dy = 0;
        levens = 5;
        dood = false;
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
            if(levens == 0){
                   dood = true;  
            }
        }
    }
    
    public int getLevens(){
        return levens;
    }
    
    public boolean getDood(){
        return dood;
    }

    public void resetGame() {
        x = 10;
        y = 10;
        levens = 5;
        dood = false;
    }

    public void links() {
        x = x - 20;
        dx = -1;
    }

    public void rechts() {
        x = x + 20;
        dx = 1;
    }

    public void boven() {
        y = y - 20;
        dy = 1;
    }

    public void onder() {
        y = y + 20;
        dy = -1;
    }

    public void reset() {
        x = 10;
        y = 10;

    }

    public int setMaxXBorder() {
        if (x > 680 - getStraal()) {
            x = 680 - getStraal();
            return x;
        }
        return x;
    }

    public int setMinXBorder() {
        if (x < 10) {
            x = 10;
            return x;
        }
        return x;
    }

    public int setMaxYBorder() {
        if (y > 460 - getStraal()) {
            y = 460 - getStraal();
            return y;
        }
        return y;
    }

    public int setMinYBorder() {
        if (y < 10) {
            y = 10;
            return y;
        }
        return y;
    }
    public int getStraal(){
        return straal;
    }
}
