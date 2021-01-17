/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Spook;

/**
 *
 * @author Gus Geurts
 */
public final class SpookView extends Region {

    private final Spook spook;
    private AnchorPane paneel;
    private Circle bal;

    public SpookView(Spook spook) {
        this.spook = spook;
        createSpook();

    }

    public void createSpook() {

        bal = new Circle(spook.getStraal());
        Image img = new Image("https://i.gifer.com/origin/d5/d5b9ae79f5254caaf0fdcf2affcec5b0_w200.gif");
        bal.setFill(new ImagePattern(img));

        getChildren().add(bal);
    }

    public Circle getVormSpook() {
        return bal;
    }
}
