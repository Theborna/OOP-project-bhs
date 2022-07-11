package com.electro.controllers.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.electro.App;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInRight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class LoginController implements Initializable {

    private static String darkPath = App.class.getResource("css/loginDark.css").toExternalForm(),
            lightPath = App.class.getResource("css/loginLight.css").toExternalForm();

    @FXML
    private Button btnForgot, btnSignIn, btnSignUp, btnRegister;
    @FXML
    private AnchorPane signIn, signUp;

    private AnchorPane inFront;
    private JMetro metro;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initButton(btnForgot);
        initButton(btnSignIn);
        initButton(btnSignUp);
        initButton(btnRegister);
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
    void btnHandler(ActionEvent event) {
        Object o = event.getSource();
        if (!(o instanceof Button))
            return;
        Button b = (Button) o;
        if (b == btnSignUp) {
            switchTo(signUp);
        } else if (b == btnRegister) {
            register();
            switchTo(signIn);
        }
    }

    private void register() { // actually register the account
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

    private void switchTo(AnchorPane pane) {
        if (pane == inFront)
            return;
        pane.toFront();
        new SlideInRight(pane).play();
        inFront = pane;
    }
}
