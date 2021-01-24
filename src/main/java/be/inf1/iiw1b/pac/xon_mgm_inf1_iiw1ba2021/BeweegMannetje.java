package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.util.TimerTask;
import javafx.application.Platform;
import model.Mannetje;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class BeweegMannetje extends TimerTask {

    private final Mannetje mannetje;
    private final SpeelveldController controller;

    /**
     * @param mannetje is het model van het mannetje
     * @param controller is de controller van het speelveld Deze methode geeft
     * alle variabelen een begin/start waarde
     */
    public BeweegMannetje(Mannetje mannetje, SpeelveldController controller) {
        this.mannetje = mannetje;
        this.controller = controller;
    }

    /**
     * deze methode definieert wat er regelmatig moet gebeuren
     */
    @Override
    public void run() {
        mannetje.Tick();        // definieert wat het mannejte regelmatig moet doen
        Platform.runLater(controller::update);
    }

}
