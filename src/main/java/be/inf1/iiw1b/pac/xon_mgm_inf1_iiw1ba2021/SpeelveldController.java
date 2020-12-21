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
import view.MannetjeView;

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
    private MannetjeView view;

    @FXML
    void initialize() {
        mannetje = new Mannetje(0,0);
        view = new MannetjeView(mannetje);
        speelveld.getChildren().add(view);
        update();
        
        speelveld.setOnKeyPressed(this::loopRond);
        resetButton.setOnAction(this::reset);
        
        view.setFocusTraversable(true);
        resetButton.setFocusTraversable(false);
    }
    private void update(){
        view.update();
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
            
        }
        update();
    }
    private void reset(ActionEvent e){
        mannetje.reset();
        update();
    }
}
