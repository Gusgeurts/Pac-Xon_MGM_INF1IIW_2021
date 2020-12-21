/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Mannetje;

/**
 *
 * @author Gus Geurts
 */
public class MannetjeView extends Region{
    
    private Mannetje mannetje;
    private AnchorPane paneel;
    
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
        Rectangle vierkant = new Rectangle(30,30, Color.BLACK);
        
        paneel.getChildren().addAll(vierkant);
            
  
    }
}
