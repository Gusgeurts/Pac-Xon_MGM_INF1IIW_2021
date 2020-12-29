/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Vak;

/**
 *
 * @author Gus Geurts
 */
public class VakView extends Region {
    
    private Vak vak;

    public VakView(Vak vak) {
        this.vak = vak;
        update();
    }
    
    public void update(){
        Rectangle v = new Rectangle(vak.getZijde(), vak.getZijde());
        v.setFill(Color.BLUE);
        v.setStroke(Color.BLACK);
        v.setStrokeWidth(1);
        
        getChildren().add(v); 
    }
}
