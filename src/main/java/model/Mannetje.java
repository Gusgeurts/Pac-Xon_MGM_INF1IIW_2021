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

    public Mannetje(int x, int y) {
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
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
    
    public void isDood(){
        if (levens != 0){
            levens --;
        }
        else{
         dood = true;   
        }
    }
    
    public void reset(){
        x = 0;
        y = 0;
    }
    
    public void links(){
            x = x - 20;
            dx = -1;          
    }
    public void rechts(){
        x = x + 20;
        dx = 1;
    } 
    public void boven(){
        y = y - 20;
        dy = 1;
    }    
    public void onder(){
        y = y + 20;
        dy = -1;
    }  
    public void stil(){
        dx = 0;
        dy = 0;
        
    }
    public int setMaxXBorder(){
        if (x>600){
            x = 600;
            return x; 
        }
        return x; 
    }
    
    public int setMinXBorder(){
        if (x<0){
            x = 0;
            return x; 
        }
        return x;
    }
        public int setMaxYBorder(){
        if (y>500){
            y = 500;
            return y; 
        }
        return y; 
    }
    
    public int setMinYBorder(){
        if (y<0){
            y = 0;
            return y; 
        }
        return y;
    }
}