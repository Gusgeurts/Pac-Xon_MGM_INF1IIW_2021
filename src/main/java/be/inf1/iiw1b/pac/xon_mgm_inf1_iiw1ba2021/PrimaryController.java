package be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
