module com.electro {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires AnimateFX;
    requires shichimifx;
    requires transitive org.jfxtras.styles.jmetro;

    opens com.electro.controllers.components to javafx.fxml, AnimateFX, org.jfxtras.styles.jmetro;
    opens com.electro.controllers.views to javafx.fxml, AnimateFX, shichimifx, org.jfxtras.styles.jmetro;
    opens com.electro to javafx.fxml, AnimateFX, org.jfxtras.styles.jmetro;

    exports com.electro.controllers.components;
    exports com.electro.controllers.views;
    exports com.electro;
}
