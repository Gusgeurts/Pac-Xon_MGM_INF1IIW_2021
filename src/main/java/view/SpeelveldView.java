/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.layout.Region;
import model.Speelveld;
import model.Vak;

/**
 *
 * @author Gus Geurts
 */
public class SpeelveldView extends Region {
    
    private Speelveld speelveld;
    private Vak vak;
    
    public SpeelveldView(Speelveld speelveld){
        this.speelveld = speelveld;
        update();
    }
    
    public void update(){
        
        int n = 0;
        getChildren().clear();
        Vak vakken[][] = speelveld.getVakken();
        for(int i=1; i<= speelveld.getRijen(); i++){
            for(int j=1; i<= speelveld.getKolommen(); j++){
                VakView vv = new VakView(vakken[i][j]);
                vv.setTranslateX(vak.getZijde() * n);
                n++;
                getChildren().add(vv);
            }
                

                
        }
        
    }
    
}
