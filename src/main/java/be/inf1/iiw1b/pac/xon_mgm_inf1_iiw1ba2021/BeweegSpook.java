/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.util.TimerTask;
import javafx.application.Platform;
import model.Spook;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class BeweegSpook extends TimerTask {

    private final Spook spook;
    private final SpeelveldController controller;

    /**
     * @param spook is het model van het spook
     * @param controller is de controller van het speelveld 
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public BeweegSpook(Spook spook, SpeelveldController controller) {
        this.spook = spook;
        this.controller = controller;
    }

    /**
     * deze methode...............
     */
    @Override
    public void run() {
        spook.Tick();
        Platform.runLater(controller::update);

    }

}
