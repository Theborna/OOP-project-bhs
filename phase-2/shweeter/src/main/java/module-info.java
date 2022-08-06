module com.electro {
        requires transitive javafx.controls;
        requires transitive javafx.fxml;
        requires AnimateFX;
        requires shichimifx;
        requires transitive org.jfxtras.styles.jmetro;
        requires org.controlsfx.controls;
        requires transitive java.desktop;
        requires java.sql;
        requires telegrambots;
        requires telegrambots.meta;
        requires webcam.capture;
        requires java.dotenv;

        opens com.electro.controllers.components
                        to javafx.fxml, AnimateFX, org.jfxtras.styles.jmetro, org.controlsfx.controls, java.desktop;
        opens com.electro.controllers.views
                        to javafx.fxml, AnimateFX, shichimifx, org.jfxtras.styles.jmetro, org.controlsfx.controls,
                        java.desktop;
        opens com.electro to javafx.fxml, AnimateFX, org.jfxtras.styles.jmetro, org.controlsfx.controls, java.desktop;
        opens com.electro.util
                        to javafx.fxml, AnimateFX, shichimifx, org.jfxtras.styles.jmetro, org.controlsfx.controls,
                        java.desktop;
        opens com.electro.views.component to webcam.capture;
        opens com.electro.phase1;
        opens com.electro.database to java.sql;

        exports com.electro.controllers.components;
        exports com.electro.controllers.views;
        exports com.electro;
        exports com.electro.phase1;
        exports com.electro.database;
}
