/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import model.Spoken;
import model.Spook;
import be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021.StartMenuController;

/**
 *
 * @author Gus Geurts/Michiel Meurice
 */
public final class SpokenView extends Region {

    private final Spoken spoken;
    private final ArrayList<SpookView> spokenView;

    /**
     * @param spoken is het model van spoken
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public SpokenView(Spoken spoken) {
        this.spoken = spoken;
        spokenView = new ArrayList<>();
        maakSpoken();
        update();
    }

    /**
     * deze methode maakt een opgegeven aantal spoken aan 
     */
    public void maakSpoken() {
        ArrayList<Spook> s = spoken.getSpoken();
        for (int i = 0; i < StartMenuController.getAantalSpoken(); i++) {
            SpookView sv = new SpookView(s.get(i));
            getChildren().add(sv);
            spokenView.add(sv);
        }
    }

    /**
     * deze methode update het spokenView
     */
    public void update() {
        ArrayList<Spook> s = spoken.getSpoken();
        for (int i = 0; i <= s.size() - 1; i++) {
            Spook spook = s.get(i);
            Node spookNode = getChildren().get(i);

            spookNode.setTranslateX((spook.getX()));
            spookNode.setTranslateY(spook.getY());

            spokenView.get(i).setOgen();

        }

    }

}
