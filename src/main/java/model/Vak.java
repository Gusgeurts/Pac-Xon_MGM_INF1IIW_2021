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
public class Vak {

    private int zijde;
    private StatusVak status;
    private boolean gevaar;

    public Vak() {
        zijde = 20;
        status = status.LEEG;
        gevaar = false;
    }

    /**
     * @return the zijde
     */
    public int getZijde() {
        return zijde;
    }

    /**
     * @return the status
     */
    public StatusVak getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusVak status) {
        this.status = status;
    }
    public void setGevaar(boolean gevaar){
        this.gevaar = gevaar;
    }
    public boolean getGevaar(){
        return gevaar;
    }
}
