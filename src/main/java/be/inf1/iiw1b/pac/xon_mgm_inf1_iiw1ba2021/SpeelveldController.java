package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Mannetje;
import model.Speelveld;
import model.Spook;
import model.Vak;
import view.MannetjeView;
import view.SpeelveldView;
import view.SpookView;

public class SpeelveldController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane speelveld;

    @FXML
    private Button resetButton;

    @FXML
    private Label labelX;

    @FXML
    private Label labelY;
    
    private Mannetje mannetje;
    private MannetjeView mannetjeView;
    private Spook spook;
    private SpookView spookView;
    private Speelveld vakkenSpeelveld;
    private SpeelveldView vakkenSpeelveldView;

    @FXML
    void initialize() {
        mannetje = new Mannetje(0,0);
        spook = new Spook(200 ,200);
        vakkenSpeelveld = new Speelveld(40, 40);
        
        mannetjeView = new MannetjeView(mannetje);
        spookView = new SpookView(spook);
        vakkenSpeelveldView = new SpeelveldView(vakkenSpeelveld);
        
        speelveld.getChildren().add(mannetjeView);
        speelveld.getChildren().add(spookView);
        speelveld.getChildren().add(vakkenSpeelveldView);
        
        update();
        
        speelveld.setOnKeyPressed(this::loopRond);
        resetButton.setOnAction(this::reset);
        
        mannetjeView.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
    }
    private void update(){
        mannetjeView.update();
        spookView.update();
        vakkenSpeelveldView.update();
        
        labelX.setText(mannetje.getX() + "");
        labelY.setText(mannetje.getY() + "");
        
    }
    
    private void loopRond(KeyEvent t){
        switch(t.getCode()){
            case RIGHT:
                mannetje.rechts();
                mannetje.setMaxXBorder();
                break;
            case LEFT:
                mannetje.links();
                mannetje.setMinXBorder();
                break;        
            case UP:
                mannetje.boven();
                mannetje.setMinYBorder();
                break;
            case DOWN:
                mannetje.onder();
                mannetje.setMaxYBorder();
                break;
            case Z:
                spook.omhoog();
                spook.setMinY();
                break;
            case S:
                spook.omlaag();
                spook.setMaxY();
                break;
            case D:
                spook.rechts();
                spook.setMaxX();
                break;
            case Q:
                spook.links();
                spook.setMinX();
                break;
            
        }
        update();
    }
    private void reset(ActionEvent e){
        mannetje.reset();
        update();
    }
}
