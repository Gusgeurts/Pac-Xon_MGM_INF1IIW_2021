/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import model.Mannetje;

/**
 *
 * @author Gus Geurts
 */
public class MannetjeView extends Region{
    
    private Mannetje mannetje;
    private AnchorPane paneel;
    private Circle cirkel;
    
    public MannetjeView(Mannetje mannetje){
        this.mannetje = mannetje;
        createMannetje();
        update();
    }
    
    public void update(){
        getChildren().clear();
        
        paneel.setTranslateX(mannetje.getX());
        paneel.setTranslateY(mannetje.getY());
        
        getChildren().addAll(paneel);
    }
    
    public void createMannetje(){
        paneel = new AnchorPane();
        
        cirkel = new Circle(mannetje.getStraal());
        Image img = new Image("https://1.bp.blogspot.com/-XXAXYwSHQb0/WSQ66A2JiuI/AAAAAAAAAi0/TOWOBq9p1-wcUETDp2tzE_OeqhhoseWuwCLcB/s1600/pacman-eating.gif");
        cirkel.setFill(new ImagePattern(img));
        
        paneel.getChildren().addAll(cirkel);
  
    }
    public Circle getVormMannetje(){
        return cirkel;
    }
}
