module be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;

    opens be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021 to javafx.fxml;
    exports be.inf1.iiw1b.pac.xon_mgm_inf1_iiw1ba2021;

    requires com.google.gson;
    opens model to com.google.gson;
}
