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
    private final Speelveld speelveld;

    /**
     * @param aantalSpoken is het aantalspoken aanwezig in het speelveld
     * @param vak is een vak van het speelveld
     * @param speelveld is het aangemaakt speelveld 
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Spoken(int aantalSpoken, Vak vak, Speelveld speelveld) {
        this.aantalSpoken = StartMenuController.getAantalSpoken();
        this.vak = vak;
        this.speelveld = speelveld;
        maakSpoken();
    }

    /**
     * Maakt een opgeven aantalspoken aan op een random positie binnen het
     * speelveld op een leeg vak
     */
    public void maakSpoken() {
        spoken = new ArrayList<>();
        for (int i = 0; i < aantalSpoken; i++) {

            double xWaarde = (Math.random() * vak.getZijde() * speelveld.getKolommen());
            double yWaarde = (Math.random() * vak.getZijde() * speelveld.getRijen());

            if (xWaarde < vak.getZijde() * 2) {  //niet in gevuld vak spawnen aan de linkerkant van het speelveld    
                xWaarde = vak.getZijde() * 2;
            }

            if (xWaarde > vak.getZijde() * speelveld.getKolommen() - 2 * vak.getZijde()) { //niet in gevuld vak spawnen aan de rechterkant van het speelveld 
                xWaarde = vak.getZijde() * speelveld.getKolommen() - 2 * vak.getZijde();
            }

            if (yWaarde < vak.getZijde() * 2) {  //niet in gevuld vak spawnen aan de bovenkant van het speelveld
                yWaarde = vak.getZijde() * 2;
            }

            if (yWaarde > vak.getZijde() * speelveld.getRijen() - 2 * vak.getZijde()) {  //niet in gevuld vak spawnen aan de onderkant van het speelveld
                yWaarde = vak.getZijde() * speelveld.getRijen() - 2 * vak.getZijde();
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
