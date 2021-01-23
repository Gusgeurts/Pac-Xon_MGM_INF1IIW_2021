package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class Speelveld {

    private final Mannetje mannetje;
    private final int teVullenVakken;
    private final Vakken vakkenVeld;
    private final Spoken spoken;

    /**
     * @param vakkenVeld is het aangemaakte speelveld
     * @param mannetje is het model van het mannetje
     * @param spoken is het model van de spoken
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Speelveld(Vakken vakkenVeld, Mannetje mannetje, Spoken spoken) {
        this.mannetje = mannetje;
        this.vakkenVeld = vakkenVeld;
        this.spoken = spoken;
        teVullenVakken = vakkenVeld.getRijen() * vakkenVeld.getKolommen() - (2 * vakkenVeld.getRijen() + 2 * (vakkenVeld.getKolommen() - 2));

    }
    
    /**
     * deze methode update het speelveld en checkt of de game al gewonnen of verloren is 
     * ook wordt er gekeken als er een gevulde lijn is gemaakt om vervolgens een veld/vak te kleuren
     */
    public void updateSpeelveld() {

        gameGewonnen();
        gameOver();
        stilInGevuld();
        maakInDeMaakLijn();
        raakInDeMaak();

        if (maakGevuldeLijn()) {
            vullenVakkenVeld();
        }

    }
    
    /**
     * deze methode reset het veld en maakt alle vakken leeg binnenin de border
     */
    public void resetVeld() {
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 1; i < vakkenVeld.getRijen() - 1; i++) {   //methode start met i=1 en stopt bij getRijen() -1 omdat de border niet op status leeg mag gezet worden
            for (int j = 1; j < vakkenVeld.getKolommen() - 1; j++) {
                vakken[i][j].setStatus(StatusVak.LEEG);
            }
        }
    }

    /**
     * deze methode zorgt ervoor dat het volledige pad(in_de_maak) status leeg krijgt wanneer er een spook doorheen gaat 
     */
    public void geraaktInPad() {
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK)) {
                    vakken[i][j].setStatus(StatusVak.LEEG);
                }
            }
        }
    }

    /**
     * @return deze method vult alle vakken met status in_de_maak wanneer het mannetje terug op een gevuld vak komt
     * de boolean f wordt gebruikt in de update methode met een if loop om te controleren wanneer er een gevulde lijn is gemaakt zodat de vul-functie kan worden gestart
     */
    public boolean maakGevuldeLijn() {
        boolean f = false;
        Vak vakken[][] = vakkenVeld.getVakken();
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.GEVULD)
                && mannetje.getX() != 0 && mannetje.getY() != 0) {
            resetGevaar();
            for (int i = 0; i < vakkenVeld.getRijen(); i++) {
                for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                    if ((vakken[i][j].getStatus().equals(StatusVak.IN_DE_MAAK))) {
                        vakken[i][j].setStatus(StatusVak.GEVULD);

                        f = true;
                    }
                }
            }
        }
        return f;
    }

    /**
     * deze methode zorgt ervoor dat wanneer het mannetje over een leeg vak beweegt dat dit vak de status "in de maak" krijgt 
     */
    public void maakInDeMaakLijn() {
        Vak vakken[][] = vakkenVeld.getVakken();
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.LEEG)) {
            vakken[mannetje.getVakY()][mannetje.getVakX()].setStatus(StatusVak.IN_DE_MAAK);
        }
    }

    /**
     * deze methode zorgt ervoor dat het mannetje dood gaat wanneer hij zijn eigen pad raakt 
     */
    public void raakInDeMaak() {
        Vak vakken[][] = vakkenVeld.getVakken();
        if (mannetje.getVakX() + 1 < vakkenVeld.getKolommen()       //als het niet een border is
                && mannetje.getVakX() - 1 >= 0
                && mannetje.getVakY() + 1 < vakkenVeld.getRijen()
                && mannetje.getVakY() - 1 >= 0) {
            if (vakken[mannetje.getVakY()][mannetje.getVakX() + 1].getStatus().equals(StatusVak.IN_DE_MAAK)) {      // rechts van mannetje  
                vakken[mannetje.getVakY()][mannetje.getVakX() + 1].setGevaar(true);
            }
            if (vakken[mannetje.getVakY()][mannetje.getVakX() - 1].getStatus().equals(StatusVak.IN_DE_MAAK)) {      // links van mannetje
                vakken[mannetje.getVakY()][mannetje.getVakX() - 1].setGevaar(true);
            }
            if (vakken[mannetje.getVakY() + 1][mannetje.getVakX()].getStatus().equals(StatusVak.IN_DE_MAAK)) {      // onder mannetje
                vakken[mannetje.getVakY() + 1][mannetje.getVakX()].setGevaar(true);
            }
            if (vakken[mannetje.getVakY() - 1][mannetje.getVakX()].getStatus().equals(StatusVak.IN_DE_MAAK)) {      // boven mannetje
                vakken[mannetje.getVakY() - 1][mannetje.getVakX()].setGevaar(true);
            }
        }
        if (vakken[mannetje.getVakY()][mannetje.getVakX()].getGevaar()) {       //wanneer het vak van het mannetje een gevaar vormt/gevaar true is wordt het mannetje gedood en wordt het pad terug leeg met de geraaktInPad() functie 
            mannetje.isDood();
            geraaktInPad();
        }
    }

    /**
     * deze functie reset het gevaar dat gegeven is door het spoor van het mannetje
     */
    public void resetGevaar() {
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                vakken[i][j].setGevaar(false);
            }
        }
    }

    /**
     * double n geeft weer hoeveel vakken zijn gevuld
     * @return deze methode geeft het percentage terug van in hoevere het speelveld gevuld is
     */
    public int getProcentGevuld() {
        Vak vakken[][] = vakkenVeld.getVakken();
        double n = 0;                                               
        for (int i = 1; i < vakkenVeld.getRijen() - 1; i++) {               //door het starten bij i = 1 en het -1 doen van het getRijen() wordt de border niet meegenomen
            for (int j = 1; j < vakkenVeld.getKolommen() - 1; j++) {
                if (vakken[i][j].getStatus().equals(StatusVak.GEVULD)) {
                    n++;
                }
            }
        }
        return (int) ((n / teVullenVakken) * 100);   //berekend het percentage gevulde vakken aan de hand van     
    }

    /**
     * @return deze methode gaat na of het vak waarin het mannetje zich bevindt gevuld is
     */
    public boolean isVakMannetjeGevuld() {
        Vak vakken[][] = vakkenVeld.getVakken();
        return vakken[mannetje.getVakY()][mannetje.getVakX()].getStatus().equals(StatusVak.GEVULD);
    }

    /**
     * deze methode zorgt ervoor dat het mannetje niet automatische beweegt wanneer het mannetje zich op een gevuld vak bevindt en dus stil staat
     */
    public void stilInGevuld() {
        if (isVakMannetjeGevuld() && (mannetje.getVx() != 0 || mannetje.getVy() != 0)) {
            mannetje.setVx(0);
            mannetje.setVy(0);
        }
    }

    /**
     * deze methode zorgt ervoor dat wanneer het mannetje dood is en geen levens meer over heeft de game wordt gereset en doodnotificatie wordt gestart
     */
    public void gameOver() {
        if (mannetje.getDood()) {
            doodNotificatie();
            resetVeld();
            mannetje.resetGame();
        }
    }
    
    /**
     * deze methode laat een notificatie zien wanneer je dood bent gegaan
     */
    public void doodNotificatie() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PacXon");
            alert.setContentText("Je bent dood gegaan!!! \nJe vulde " + getProcentGevuld() + "% van de vakken");
            alert.show();
        }

    /**
     * deze methode kijkt als er minstens 80% gevuld is en wanneer dit zo is wordt de game gereset en wordt winnotificatie gestart
     */
    public void gameGewonnen() {
        if (getProcentGevuld() >= 80) {
            winNotificatie();
            resetVeld();
            mannetje.resetGame();
        }
    }
    
    /**
     * deze methode laat een notificatie zien wanneer je hebt gewonnen
     */
    public void winNotificatie() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pac-Xon");
        alert.setContentText("Je hebt gewonnen!!!\nje vulde " + getProcentGevuld() + " % met nog " + mannetje.getLevens() + " levens over");
        alert.show();
    }
    
    /**
     * deze methode bekijkt de locatie van spoken en roept vervolgens de spookcheck methode op
     * vervolgens worden alle vakken die check false zijn op status gevuld gezet 
     * daarna wordt de check functie gereset door alle vakken op scan false te zetten
     * deze methode is in samenwerking geschreven/bedacht met een vriend TIBO STANS hij heeft ons de recursieve methode uitgelegd en ons een andere inzicht gegeven op ons probleem
     */
    public void vullenVakkenVeld() {
        Vak[][] vakken = vakkenVeld.getVakken();
        ArrayList<Spook> s = spoken.getSpoken();
        for (int t = 0; t <= s.size() - 1; t++) {        //hier wordt gekeken naar de locatie van alle spoken 
            int i = spoken.getSpoken().get(t).getVakY();
            int j = spoken.getSpoken().get(t).getVakX();
            spookCheck(i, j);                                    //methode spookcheck oproepen met x en y coördinaat van een spook 
        }

        for (int i = 0; i < vakkenVeld.getRijen(); i++) {           //zet alle vakken die true zijn en dus gevuld mogen worden op status gevuld
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                if (vakken[i][j].getCheck() == false) {
                    vakken[i][j].setStatus(StatusVak.GEVULD);

                }
            }
        }
        
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {           //zet alles terug op true voor volgende keer spookcheck
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                vakken[i][j].setCheck(false);
            }
        }
    }

    /**
     * @param x is de x-coördinaat die gechecked wordt, initieel is dit de x-coördinaat van een spook
     * @param y is de y-coördinaat die gechecked wordt, initieel begin is dit de y-coördinaat van een spook
     * deze methode onderscheidt de verschillende vakken die gecreërd worden bij het maken van lijn in het speelveld deze vakken worden van elkaar onderscheiden doordat
     * het vak waarin het spook zit check true wordt gezet bij de inliggende vakken. Het vak waarin dus geen spook zit zal check false zijn. Zie visualisatie word: systeem spookcheck
     */
    public void spookCheck(int x, int y) {
        Vak[][] vakken = vakkenVeld.getVakken();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j =  y - 1; j <= y + 1; j++) {
                if (vakken[i][j].getStatus() == StatusVak.LEEG && vakken[i][j].getCheck() == false) {   //als het vak leeg is en er nog geen check op is uitgevoerd
                    vakken[i][j].setCheck(true);
                    spookCheck(i, j);       //neemt het vak dat net is gecheckt als het vak waaruit nieuw check plaats vindt
                }

            }
        }
    }
}
