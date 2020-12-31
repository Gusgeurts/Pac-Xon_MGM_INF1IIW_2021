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
 * @author Gus Geurts
 */
public class VakView extends Region {

    private Vak vak;
    private Rectangle v;
    private StatusVak status;

    public VakView(Vak vak) {
        this.vak = vak;
        update();
    }

    public void update() {
        v = new Rectangle(vak.getZijde(), vak.getZijde());

        if (vak.getStatus() == status.LEEG) {
            v.setFill(Color.BLACK);
            v.setStroke(Color.GREY);
            v.setStrokeWidth(1);
        } else if (vak.getStatus() == status.IN_DE_MAAK) {
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

    public Rectangle getVormVak() {
        return v;
    }
    public StatusVak getStatus(){
        return status;
    }
    
    }
