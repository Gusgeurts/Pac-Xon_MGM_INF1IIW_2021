/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021.StartMenuController;

/**
 *
 * @author Gus Geurts
 */
public final class Spoken {

    private ArrayList<Spook> spoken;
    private final int aantalSpoken;
    private final Vak vak;
    private final Speelveld speelveld;

    public Spoken(int aantalSpoken, Vak vak, Speelveld speelveld) {
        this.aantalSpoken = StartMenuController.getAantalSpoken();
        this.vak = vak;
        this.speelveld = speelveld;
        maakSpoken();
    }

    public void maakSpoken() {
        spoken = new ArrayList<>();
        for (int i = 0; i < aantalSpoken; i++) {

            double xWaarde = (Math.random() * vak.getZijde() * speelveld.getKolommen());
            double yWaarde = (Math.random() * vak.getZijde() * speelveld.getRijen());

            if (xWaarde < vak.getZijde() * 2) {
                xWaarde = vak.getZijde() * 2;
            }
            if (yWaarde < vak.getZijde() * 2) {
                yWaarde = vak.getZijde() * 2;
            }

            if (xWaarde > vak.getZijde() * speelveld.getKolommen() - 2 * vak.getZijde()) {
                xWaarde = vak.getZijde() * speelveld.getKolommen() - 2 * vak.getZijde();
            }
            if (yWaarde > vak.getZijde() * speelveld.getRijen() - 2 * vak.getZijde()) {
                yWaarde = vak.getZijde() * speelveld.getRijen()- 2 * vak.getZijde();
            }
            spoken.add(new Spook(xWaarde, yWaarde));
        }
    }

    public ArrayList<Spook> getSpoken() {
        return spoken;
    }

    ///public int getAantalSpoken() {
    //  return aantalSpoken;
   //

}
