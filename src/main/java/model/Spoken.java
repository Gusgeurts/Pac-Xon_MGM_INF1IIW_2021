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
     * @param vakkenVeld zijn de aangemaakte vakken van het speelveld
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Spoken(Vak vak, Vakken vakkenVeld) {
        this.vak = vak;
        this.vakkenVeld = vakkenVeld;
        this.aantalSpoken = StartMenuController.getAantalSpoken();
        maakSpoken();
    }

    /**
     * Maakt een opgeven aantalspoken aan op een random positie binnen het speelveld op een leeg vak
     */
    public void maakSpoken() {
        spoken = new ArrayList<>();
        for (int i = 0; i < aantalSpoken; i++) {
            double randx = Math.random();           //genereert een random x-coördinaat tussen 0 en 1
            double randy = Math.random();           //genereert een random x-coördinaat tussen 0 en 1

            double xWaarde = (randx * vak.getZijde() * vakkenVeld.getKolommen());       //zet de random x om in een vak x coördinaat
            double yWaarde = (randy * vak.getZijde() * vakkenVeld.getRijen());           //zet de random y om in een vak-y-coördinaat

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
