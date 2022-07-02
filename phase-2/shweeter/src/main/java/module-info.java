module com.electro {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens com.electro.controllers.components to javafx.fxml;
    opens com.electro.controllers.views to javafx.fxml;
    opens com.electro to javafx.fxml;

    exports com.electro.controllers.components;
    exports com.electro.controllers.views;
    exports com.electro;
}
