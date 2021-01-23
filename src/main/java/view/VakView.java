/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.StatusVak;
import model.Vak;

/**
 *
 * @author Gus Geurts/Michiel Meurice
 */
public final class VakView extends Region {

    private final Vak vak;
    private Rectangle v;
    private StatusVak status;

    /**
     * @param vak is het model van het vak
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public VakView(Vak vak) {
        this.vak = vak;
        update();
    }

    /**
     * de methode update het VakView
     */
    public void update() {
        v = new Rectangle(vak.getZijde(), vak.getZijde());

        if (vak.getStatus() == StatusVak.LEEG) {
            v.setFill(Color.BLACK);
            v.setStroke(Color.GREY);
            v.setStrokeWidth(1);
        } else if (vak.getStatus() == StatusVak.IN_DE_MAAK) {
            v.setFill(Color.AQUA);
            v.setStroke(Color.GREY);
            v.setStrokeWidth(1);
        } else {
            v.setFill(Color.BLUE);
            v.setStroke(Color.GREY);
            v.setStrokeWidth(1);
        }
        getChildren().add(v);
    }

    /**
     * @return deze methode geeft de vorm van het vak terug 
     */
    public Rectangle getVormVak() {
        return v;
    }

    /**
     * @return geeft de status van het vak terug
     */
    public StatusVak getStatus() {
        return status;
    }

}
