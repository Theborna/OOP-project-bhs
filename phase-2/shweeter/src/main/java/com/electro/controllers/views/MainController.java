package com.electro.controllers.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.electro.App;
import com.electro.controllers.components.chatItemController;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import de.jensd.shichimifx.utils.SplitPaneDividerSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class MainController implements Initializable {

    private static String darkPath = App.class.getResource("css/styleDark.css").toExternalForm(),
            lightPath = App.class.getResource("css/styleLight.css").toExternalForm();

    @FXML
    private Button btnChat, btnExplore, btnFeed, btnLogout, btnNotification, btnProfile, btnSaved, btnSettings;

    @FXML
    private ToggleButton tglClose;

    @FXML
    private AnchorPane pnChat, pnExplore, pnFeed, pnNotifications, pnProfile, pnSettings;

    @FXML
    private SplitPane splPane, splChat;

    @FXML
    private VBox vboxChat;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    private ToggleButton tglChat;

    private AnchorPane inFront;
    private JMetro metro;

    @FXML
    private void SwitchScene(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnProfile) {
        } else if (btn == btnFeed) {
            switchTo(pnFeed);
        } else if (btn == btnChat) {
            switchTo(pnChat);
        } else if (btn == btnExplore) {
            switchTo(pnExplore);
        } else if (btn == btnNotification) {
            switchTo(pnNotifications);
        } else if (btn == btnSettings) {
            switchTo(pnSettings);
        } else if (btn == btnProfile) {
            switchTo(pnProfile);
        }
    }

    @FXML
    private void logout() throws IOException {
        FXMLLoader loader = App.loadFXML("login");
        Parent root = loader.load();
        App.setRoot(root);
        ((LoginController) loader.getController()).setStyle(App.getStyle());
        metro = null;
        // new SlideInUp(App.getScene().getRoot()).play();
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
        metro.getOverridingStylesheets().remove(((style != Style.DARK) ? darkPath : lightPath));
        metro.getOverridingStylesheets().add(((style == Style.DARK) ? darkPath : lightPath));
        App.setStyle(style);

    }

    private void switchTo(AnchorPane pane) {
        if (pane == inFront)
            return;
        pane.toFront();
        new SlideInRight(pane).play();
        inFront = pane;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initSlidingPane();
        initChatItems();
    }

    private void initSlidingPane() {
        tglChat = new ToggleButton();
        SplitPaneDividerSlider leftSplitPaneDividerSlider = new SplitPaneDividerSlider(splPane, 0,
                SplitPaneDividerSlider.Direction.LEFT);
        SplitPaneDividerSlider chatsSplitPaneDividerSlider = new SplitPaneDividerSlider(splChat, 0,
                SplitPaneDividerSlider.Direction.RIGHT);
        tglClose.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            leftSplitPaneDividerSlider.setAimContentVisible(t1);
        });
        tglChat.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            chatsSplitPaneDividerSlider.setAimContentVisible(t1);
        });
    }

    @FXML
    private void khk() {
        tglChat.selectedProperty().set(!tglChat.selectedProperty().get());
    }

    private void initChatItems() {
        System.out.println("loading the chats...");
        Node[] nodes = new Node[20];
        try {
            for (int i = 0; i < nodes.length; i++) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("components/chatItem.fxml"));
                nodes[i] = loader.load();
                vboxChat.getChildren().add(nodes[i]);
                final int j = i;
                // nodes[j].setOnMouseEntered(event -> {
                //     nodes[j].setStyle("-fx-background-color: #43474D");
                // });
                // nodes[j].setOnMouseExited(event -> {
                //     nodes[j].setStyle("-fx-background-color: transparent");
                // });
                ((chatItemController) loader.getController()).checkSize();
                vboxChat.widthProperty().addListener(new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                        ((chatItemController) loader.getController()).checkSize();
                    }

                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
