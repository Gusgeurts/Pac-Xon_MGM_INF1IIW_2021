/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.util.TimerTask;
import javafx.application.Platform;
import model.Mannetje;

/**
 *
 * @author Gus Geurts
 */
public class BeweegMannetje extends TimerTask   {
    
    private Mannetje mannetje;
    private SpeelveldController controller;

    public BeweegMannetje(Mannetje mannetje, SpeelveldController controller) {
        this.mannetje = mannetje;
        this.controller = controller;
    }

    @Override
    public void run() {
         mannetje.Tick();
         Platform.runLater(controller::update);
    }
    
    
}
