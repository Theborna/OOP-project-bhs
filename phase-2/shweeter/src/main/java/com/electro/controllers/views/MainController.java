package com.electro.controllers.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.electro.App;
import com.electro.controllers.components.messageController;
import com.electro.views.ChatListView;
import com.electro.views.MessageListView;
import com.electro.views.PostListView;
import com.electro.views.ProfileView;

import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import de.jensd.shichimifx.utils.SplitPaneDividerSlider;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.skin.AccordionSkin;
import javafx.scene.control.skin.TitledPaneSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class MainController implements Initializable {

    private static String darkPath = App.class.getResource("css/styleDark.css").toExternalForm(),
            lightPath = App.class.getResource("css/styleLight.css").toExternalForm();

    @FXML
    private Button btnChat, btnExplore, btnFeed, btnLogout, btnNotification, btnProfile, btnSaved, btnSettings,
            btnCompose, btnPost, btnScrollPage, btnChatTop, btnMsgTop;

    @FXML
    private ToggleButton tglClose, btnChatListClose, tglChat, tglMsg;

    @FXML
    private AnchorPane pnChat, pnExplore, pnFeed, pnNotifications, pnProfile, pnSettings, pnCompose;

    @FXML
    private SplitPane splPane, splChat;

    @FXML
    private ScrollPane scrollChat, scrollPost, scrollMsg, scrollProf;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    private AnchorPane inFront;
    private JMetro metro;

    @FXML
    private void SwitchScene(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnProfile) {
            ProfileView.getOther().setUser();
            scrollProf.setContent(ProfileView.getOther());
            System.out.println("hi");
            switchToRight(pnProfile);
        } else if (btn == btnFeed) {
            switchToRight(pnFeed);
        } else if (btn == btnChat) {
            switchToRight(pnChat);
        } else if (btn == btnExplore) {
            switchToRight(pnExplore);
        } else if (btn == btnNotification) {
            switchToRight(pnNotifications);
        } else if (btn == btnSettings) {
            switchToUp(pnSettings);
        } else if (btn == btnCompose) {
            switchToUp(pnCompose);
        } else if (btn == btnPost) {
            switchToRight(pnFeed);
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

    private void switchToRight(AnchorPane pane) {
        if (pane == inFront)
            return;
        pane.toFront();
        new SlideInRight(pane).play();
        inFront = pane;
    }

    private void switchToUp(AnchorPane pane) {
        if (pane == inFront)
            return;
        pane.toFront();
        new SlideInUp(pane).play();
        inFront = pane;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initSlidingPane();
        initChatItems();
        initMessages();
        initPosts();
        System.out.println("done");
    }

    private void initSlidingPane() {
        coupleButtonAndSlider(tglChat, splChat, 0, SplitPaneDividerSlider.Direction.RIGHT);
        coupleButtonAndSlider(tglMsg, splChat, 0, SplitPaneDividerSlider.Direction.LEFT);
        coupleButtonAndSlider(tglClose, splPane, 0, SplitPaneDividerSlider.Direction.LEFT);
    }

    private void coupleButtonAndSlider(ToggleButton button, SplitPane pane, int division,
            SplitPaneDividerSlider.Direction direction) {
        if (button == null)
            button = new ToggleButton();
        SplitPaneDividerSlider slider = new SplitPaneDividerSlider(pane, division, direction);
        button.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            slider.setAimContentVisible(t1);
        });

    }

    @FXML
    private void Scroll(ActionEvent evt) {
        if (evt.getSource() == btnScrollPage) {
            scrollTo(scrollProf, true);
        } else if (evt.getSource() == btnChatTop) {
            scrollTo(scrollChat, true);
        } else if (evt.getSource() == btnMsgTop) {
            scrollTo(scrollMsg, false);
        }
    }

    private void scrollTo(ScrollPane myScrollPane, boolean top) {
        Animation animation = new Timeline(
                new KeyFrame(
                        Duration.seconds((top ? myScrollPane.getVvalue() : 1 - myScrollPane.getVvalue())
                                * myScrollPane.getHeight() * 0.003),
                        new KeyValue(myScrollPane.vvalueProperty(), top ? 0 : 1, Interpolator.EASE_OUT)));
        animation.play();
    }

    private void initChatItems() {
        ChatListView.getPnChatInstance().addAll();
        scrollChat.setContent(ChatListView.getPnChatInstance());
    }

    private void initMessages() {
        MessageListView.getInstance().addAll();
        scrollMsg.setContent(MessageListView.getInstance());
    }

    private void initPosts() {
        PostListView.getPnPostInstance().addAll();
        scrollPost.setContent(PostListView.getPnPostInstance());
    }
}
