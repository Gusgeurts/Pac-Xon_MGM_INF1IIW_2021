/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gus Geurts/Michiel Meurice
 */
public class Vakken {

    private final Vak vakken[][];
    private final int rijen;
    private final int kolommen;
    private StatusVak status;

    /**
     * @param rijen is het aantal rijen van het speelveld
     * @param kolommen is het aantal kolommen van het speelveld
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Vakken(int rijen, int kolommen) {
        this.rijen = rijen;
        this.kolommen = kolommen;
        vakken = new Vak[rijen][kolommen];

        for (int i = 0; i < rijen; i++) {
            for (int j = 0; j < kolommen; j++) {
                vakken[i][j] = new Vak();
            }
        }
    }

    /**
     * @return geeft het aantal vakken van het speelveld terug
     */
    public Vak[][] getVakken() {
        return vakken;
    }

    /**
     * @return geeft het aantal rijen van het speelveld terug
     */
    public int getRijen() {
        return rijen;
    }

    /**
     * @return geeft het aantal kolommen van het speelveld terug
     */
    public int getKolommen() {
        return kolommen;
    }
}
