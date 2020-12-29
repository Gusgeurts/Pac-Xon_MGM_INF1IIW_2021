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

    private double zijde;
    private StatusVak status;

    public Vak() {
        zijde = 20;
        status = status.LEEG;
    }

    /**
     * @return the zijde
     */
    public double getZijde() {
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

}
