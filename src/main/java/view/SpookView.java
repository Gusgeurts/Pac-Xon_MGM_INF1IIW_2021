/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Spook;

/**
 *
 * @author Gus Geurts
 */
public class SpookView extends Region {

    private Spook spook;
    private AnchorPane paneel;
    private Circle bal;

    public SpookView(Spook spook) {
        this.spook = spook;
        createSpook();

        update();
    }

    public void update() {

        getChildren().clear();

        paneel.setTranslateX(spook.getX());
        paneel.setTranslateY(spook.getY());

        getChildren().addAll(paneel);
    }

    public void createSpook() {

        paneel = new AnchorPane();
        bal = new Circle(spook.getStraal(), Color.PINK);
        
        paneel.getChildren().addAll(bal);
    }

    public Circle getVormSpook() {
        return bal;
    }
}
