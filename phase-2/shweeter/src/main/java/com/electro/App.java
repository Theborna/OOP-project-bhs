package com.electro;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static boolean mouseClick;
    private double x, y;
    private static Style style;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("login").load();
        // setMouse();
        // set stage borderless
        // stage.initStyle(StageStyle.UNDECORATED);
        // drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        scene = new Scene(root, 1050, 600);
        style = Style.DARK;
        JMetro jMetro = new JMetro(root, style);
        jMetro.getOverridingStylesheets().add(App.class.getResource("css/loginDark.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public static void setStyle(Style style) {
        App.style = style;
    }

    public static Style getStyle() {
        return style;
    }

    public static Scene getScene() {
        return scene;
    }

    private void setMouse() {
        EventHandler<MouseEvent> e = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                System.out.println(mouseClick);
                mouseClick = arg0.getButton().equals(MouseButton.PRIMARY);
            }
        };
        scene.setOnMouseMoved(e);
    }

    public static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }

}