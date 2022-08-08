package com.electro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

import com.electro.phase1.util.Log;
import com.electro.views.component.WebcamNode;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static boolean mouseClick;
    private static double x, y;
    private static Style style;
    private static FileChooser picChooser;

    @Override
    public void start(Stage stage) throws IOException {
        Log.init();
        Parent root = loadFXML("login").load();
        // setMouse();
        // set stage borderless
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Shweeter");
        stage.getIcons().add(new Image(App.class.getResource("images/icons8_source_code_96px.png").toExternalForm()));
        scene = new Scene(root, 1050, 600);
        // drag it here
        scene.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        // set File Choosers
        picChooser = new FileChooser();
        picChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("HTML Files", "*.html"));
        style = Style.DARK;
        JMetro jMetro = new JMetro(root, style);
        jMetro.getOverridingStylesheets().add(App.class.getResource("css/loginDark.css").toExternalForm());
        stage.setMinWidth(500);
        stage.setScene(scene);
        stage.show();
        Log.logger.info("application started on phase-2");
        // Image img = WebcamNode.getImage();
    }

    public static FileChooser getPicChooser() {
        return picChooser;
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

    public static Image getImage(String url) {
        return new Image(App.class.getResource(url).toExternalForm());
    }


    class WindowButtons extends HBox {
        public WindowButtons() {
            Button closeBtn = new Button("X");
            Button minimizeBtn = new Button("_");
            closeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            this.getChildren().add(closeBtn);
            this.getChildren().add(minimizeBtn);
        }
    }

}