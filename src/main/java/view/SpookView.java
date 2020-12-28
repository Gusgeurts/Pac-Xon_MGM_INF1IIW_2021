/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
        Circle bal = new Circle(spook.getStraal(), Color.BLUE);
        paneel.getChildren().addAll(bal);
    }
}
