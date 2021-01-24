/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import model.Mannetje;
import model.Speelveld;
import model.Spoken;
import model.Spook;
import model.StatusVak;
import model.Vak;
import model.Vakken;

/**
 *
 * @author Gus Geurts/Michiel Meurice
 */
public final class SpeelveldView extends Region {

    private final Mannetje mannetje;
    private final Spook spook;
    private final Spoken spoken;
    private final SpokenView spokenView;
    private final MannetjeView mannetjeView;
    private final Vakken vakkenVeld;
    private final Speelveld vakkenSpeelveld;
    private final Vak vak;

    /**
     * @param vakkenSpeelveld is het model van het speelveld
     * @param mannetje is het model van het mannetje 
     * @param spook is het model van het spook
     * @param spoken is het model van de spoken
     * @param spokenView is de view van spoken
     * @param mannetjeView is de view van het mannetje
     * @param vakkenVeld  is de model van de vakken
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public SpeelveldView(Speelveld vakkenSpeelveld, Mannetje mannetje, Spook spook, Spoken spoken, SpokenView spokenView, MannetjeView mannetjeView, Vakken vakkenVeld, Vak vak) {

        this.vakkenVeld = vakkenVeld;
        this.mannetje = mannetje;
        this.spook = spook;
        this.spoken = spoken;
        this.spokenView = spokenView;
        this.mannetjeView = mannetjeView;
        this.vakkenSpeelveld = vakkenSpeelveld;
        this.vak = vak;

        update();
    }

    /**
     * deze methode update het speelveldview
     */
    public void update() {

        getChildren().clear();

        maakSpeelveld();

    }

    /**
     * deze methode maakt een speelveld aan
     */
    public void maakSpeelveld() {
        int n = 0;
        int m = 0;
        Vak vakken[][] = vakkenVeld.getVakken();
        for (int i = 0; i < vakkenVeld.getRijen(); i++) {
            for (int j = 0; j < vakkenVeld.getKolommen(); j++) {
                VakView vv = new VakView(vakken[i][j]);
                if (i == 0) {                                   //bovenkant border
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                if (i == vakkenVeld.getRijen() - 1) {           //onderkant border
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                if (j == 0) {                                   //linkerkant border
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                if (j == vakkenVeld.getKolommen() - 1) {        //rechterkant border
                    vakken[i][j].setStatus(StatusVak.GEVULD);
                }
                vv.setTranslateX(vak.getZijde() * n);
                vv.setTranslateY(vak.getZijde() * m);
                n++;
                if (n == vakkenVeld.getKolommen()) {
                    n = 0;
                }
                switch (vakken[i][j].getStatus()) {
                    case GEVULD:
                        vv.setId("idGevuld");
                        break;
                    case IN_DE_MAAK:
                        vv.setId("idInDeMaak");
                        break;
                    default:
                        vv.setId("idLeeg");
                        break;
                }
                getChildren().add(vv);
            }
            m++;
        }
    }

    /**
     * deze methode zorgt voor de botsingen van het spook met gevulde vakken
     */
    public void spookRaaktGevuld() {
        ObservableList<Node> vakken = getChildren();
        ObservableList<Node> spoken = spokenView.getChildrenUnmodifiable();
        ArrayList<Spook> sp = this.spoken.getSpoken();
        int i = 0;

        for (Node s : spoken) {

            Bounds boundSpook = s.localToParent(s.getBoundsInLocal());

            for (Node v : vakken) {
                Bounds boundVak = v.localToParent(v.getBoundsInLocal());
                if (s.localToParent(Point2D.ZERO).getY() + spook.getStraal() >= boundVak.getMinY() - 1
                        && s.localToParent(Point2D.ZERO).getY() + spook.getStraal() <= boundVak.getMinY() + 1
                        && s.localToParent(Point2D.ZERO).getX() >= boundVak.getMinX()
                        && s.localToParent(Point2D.ZERO).getX() <= boundVak.getMinX() + boundVak.getWidth()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVy(-0.5);
                    } else if (v.getId().equals("idInDeMaak")) {  //zorgt ervoor dat het mannetje dood gaat wanneer hij wordt geraakt in zijn pad
                        mannetje.isDood();
                        vakkenSpeelveld.geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getY() - spook.getStraal() >= boundVak.getMaxY() - 1
                        && s.localToParent(Point2D.ZERO).getY() - spook.getStraal() <= boundVak.getMaxY() + 1
                        && s.localToParent(Point2D.ZERO).getX() >= boundVak.getMinX()
                        && s.localToParent(Point2D.ZERO).getX() <= boundVak.getMinX() + boundVak.getWidth()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVy(0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveld.geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getX() - spook.getStraal() >= boundVak.getMaxX() - 1
                        && s.localToParent(Point2D.ZERO).getX() - spook.getStraal() <= boundVak.getMaxX() + 1
                        && s.localToParent(Point2D.ZERO).getY() >= boundVak.getMinY()
                        && s.localToParent(Point2D.ZERO).getY() <= boundVak.getMinY() + boundVak.getHeight()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVx(0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveld.geraaktInPad();
                    }

                } else if (s.localToParent(Point2D.ZERO).getX() + spook.getStraal() >= boundVak.getMinX() - 1
                        && s.localToParent(Point2D.ZERO).getX() + spook.getStraal() <= boundVak.getMinX() + 1
                        && s.localToParent(Point2D.ZERO).getY() >= boundVak.getMinY()
                        && s.localToParent(Point2D.ZERO).getY() <= boundVak.getMinY() + boundVak.getHeight()) {
                    if (v.getId().equals("idGevuld")) {
                        sp.get(i).setVx(-0.5);
                    } else if (v.getId().equals("idInDeMaak")) {
                        mannetje.isDood();
                        vakkenSpeelveld.geraaktInPad();
                    }

                }

            }
            i++;
        }

    }

}
