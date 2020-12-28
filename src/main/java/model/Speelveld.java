/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import model.Vak;


/**
 *
 * @author Gus Geurts
 */
public class Speelveld {
    private Vak vakken[][];
    private int rijen;
    private int kolommen;

    public Speelveld(int rijen, int kolommen) {
        this.rijen = rijen;
        this.kolommen = kolommen;
        vakken = new Vak[rijen][kolommen];
        
        for (int i = 0; i<rijen; i++){
            for(int j = 0; j<kolommen; j++){
                vakken[i][j] = new Vak();
            }
        }
    }
    
    public Vak[][] getVakken(){
        return vakken;
    }

    /**
     * @return the rijen
     */
    public int getRijen() {
        return rijen;
    }

    /**
     * @return the kolommen
     */
    public int getKolommen() {
        return kolommen;
    }
    
}
