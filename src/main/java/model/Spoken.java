package model;

import java.util.ArrayList;
import be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021.StartMenuController;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public final class Spoken {

    private ArrayList<Spook> spoken;
    private final int aantalSpoken;
    private final Vak vak;
    private final Vakken vakkenVeld;

    /**
     * @param vak is een vak van het speelveld
     * @param speelveld is het aangemaakt speelveld Deze methode geeft alle
     * variabelen een begin/start waarde
     */
    public Spoken(Vak vak, Vakken vakkenVeld) {
        this.aantalSpoken = StartMenuController.getAantalSpoken();
        this.vak = vak;
        this.vakkenVeld = vakkenVeld;
        maakSpoken();
    }

    /**
     * Maakt een opgeven aantalspoken aan op een random positie binnen het speelveld op een leeg vak
     */
    public void maakSpoken() {
        spoken = new ArrayList<>();
        for (int i = 0; i < aantalSpoken; i++) {
            double randx = Math.random();
            double randy = Math.random();

            double xWaarde = (randx * vak.getZijde() * vakkenVeld.getKolommen());
            double yWaarde = (randy * vak.getZijde() * vakkenVeld.getRijen());

            if (xWaarde < vak.getZijde() * 2) {                             //niet in gevuld vak spawnen aan de linkerkant van het speelveld    
                xWaarde = vak.getZijde() * 2;
            }

            if (xWaarde > vak.getZijde() * vakkenVeld.getKolommen() - 2 * vak.getZijde()) {             //niet in gevuld vak spawnen aan de rechterkant van het speelveld 
                xWaarde = vak.getZijde() * vakkenVeld.getKolommen() - 2 * vak.getZijde();
            }

            if (yWaarde < vak.getZijde() * 2) {                              //niet in gevuld vak spawnen aan de bovenkant van het speelveld
                yWaarde = vak.getZijde() * 2;
            }

            if (yWaarde > vak.getZijde() * vakkenVeld.getRijen() - 2 * vak.getZijde()) {                //niet in gevuld vak spawnen aan de onderkant van het speelveld
                yWaarde = vak.getZijde() * vakkenVeld.getRijen() - 2 * vak.getZijde();
            }
            spoken.add(new Spook(xWaarde, yWaarde));
        }
    }

    /**
     * @return geeft een arraylist van de spoken terug
     */
    public ArrayList<Spook> getSpoken() {
        return spoken;
    }
}
