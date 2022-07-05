package com.electro.controllers.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.electro.App;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class LoginController implements Initializable {

    private static String darkPath = App.class.getResource("css/loginDark.css").toExternalForm(),
            lightPath = App.class.getResource("css/loginLight.css").toExternalForm();

    @FXML
    private Button btnForgot, btnSignIn, btnSignOut;

    private JMetro metro;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initButton(btnForgot);
        initButton(btnSignIn);
        initButton(btnSignOut);
    }

    @FXML
    private void signIn() throws IOException {
        FXMLLoader loader = App.loadFXML("main");
        Parent root = loader.load();
        App.setRoot(root);
        ((MainController) loader.getController()).setStyle(App.getStyle());
        metro = null;
        // new SlideInDown(App.getScene().getRoot()).play();
    }

    @FXML
    private void toggleTheme() throws IOException {
        Style style;
        if (App.getStyle() == Style.DARK)
            style = Style.LIGHT;
        else
            style = Style.DARK;
        setStyle(style);
    }

    public void setStyle(Style style) {
        if (metro == null)
            metro = new JMetro(App.getScene().getRoot(), style);
        metro.setStyle(style);
        metro.getOverridingStylesheets().remove((style != Style.DARK) ? darkPath : lightPath);
        metro.getOverridingStylesheets().add((style == Style.DARK) ? darkPath : lightPath);
        metro.reApplyTheme();
        App.setStyle(style);
    }

    private void initButton(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                App.getScene().setCursor(Cursor.HAND); // Change cursor to hand
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                App.getScene().setCursor(Cursor.DEFAULT); // Change cursor to hand
            }
        });
    }

}
