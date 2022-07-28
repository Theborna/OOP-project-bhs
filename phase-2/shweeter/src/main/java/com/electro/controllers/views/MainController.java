package com.electro.controllers.views;

import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.controlsfx.control.textfield.CustomTextField;

import com.electro.App;
import com.electro.controllers.components.messageController;
import com.electro.phase1.AppRegex;
import com.electro.phase1.controllers.ChatController;
import com.electro.phase1.controllers.NewChatController;
import com.electro.phase1.models.connection.ChatUserConnection;
import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.Message;
import com.electro.phase1.models.node.user.User;
import com.electro.util.ResponsiveVbox;
import com.electro.util.StretchTextArea;
import com.electro.views.ChatListView;
import com.electro.views.MessageListView;
import com.electro.views.PostListView;
import com.electro.views.ProfileView;
import com.electro.views.component.ErrorNotification;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import de.jensd.shichimifx.utils.SplitPaneDividerSlider;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.skin.AccordionSkin;
import javafx.scene.control.skin.TitledPaneSkin;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class MainController implements Initializable {

    public static String darkPath = App.class.getResource("css/styleDark.css").toExternalForm(),
            lightPath = App.class.getResource("css/styleLight.css").toExternalForm();

    @FXML
    private Button btnChat, btnExplore, btnFeed, btnLogout, btnNotification, btnProfile, btnSaved, btnSettings,
            btnCompose, btnPost, btnScrollPage, btnChatTop, btnMsgTop, btnNewChat, btnChatSettings, btnMsgSend,
            btnMsgFile;

    @FXML
    private ToggleButton tglClose, btnChatListClose, tglChat, tglMsg;

    @FXML
    private AnchorPane pnChat, pnExplore, pnFeed, pnNotifications, pnProfile, pnSettings, pnCompose, pnNewChat,
            pnForward;

    @FXML
    private AnchorPane pnSetChatName, pnSetChatType, pnSetChatMembers;

    @FXML
    private Button btnPrivate, btnGroup, btnChannel, btnAddNewChatMember, btnRemoveNewChatMember,
            btnResetNewChatMembers, btnFinalizeNewGroup, btnSetNewChatPic, btnConfirmNewChatName,
            btnForward;
    @FXML
    private CustomTextField txtChatName;

    @FXML
    private TextArea txtAMessage;

    @FXML
    private TextField txtNewMemberName;

    @FXML
    private ListView<String> lstNewChatMembers;

    @FXML
    private SplitPane splPane, splChat;

    @FXML
    private ScrollPane scrollChat, scrollPost, scrollMsg, scrollProf, scrollExplore, scrollForward;

    @FXML
    private BorderPane bpReply;

    @FXML
    private Label lblRepliedName, lblRepliedMsg;

    // @FXML
    // private BorderPane bpChatsToForward;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    private AnchorPane inFront;
    private JMetro metro;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Chat.LogToChat(1);// TODO: change this
        initSlidingPane();
        initChatItems();
        initMessages();
        initPosts();
        setExplore();
        initForward();
        lstNewChatMembers.setItems(FXCollections.observableArrayList());
        pnSetChatType.toFront();
        StretchTextArea.bind(txtAMessage);
        ResponsiveVbox.bind(ProfileView.getOther());
        System.out.println("done");
    }

    private void initForward() {
        ChatListView.getPnForwardInstance().withChat(User.getCurrentUser().getAccessibleChats());
        scrollForward.setContent(ChatListView.getPnForwardInstance());
        MessageListView.getInstance().getForwardingProperty().bindBidirectional(pnForward.visibleProperty());
        pnForward.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                if (arg2)
                    switchToUp(pnForward);
                else
                    switchToUp(pnChat);
            }
        });
    }

    @FXML
    private void SwitchScene(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnProfile) {
            ProfileView.getOther().withUser(User.getCurrentUser());
            scrollProf.setContent(ProfileView.getOther());
            System.out.println("hi");
            switchToRight(pnProfile);
        } else if (btn == btnFeed) {
            initPosts();
            switchToRight(pnFeed);
        } else if (btn == btnChat) {
            switchToRight(pnChat);
        } else if (btn == btnExplore) {
            setExplore();
            switchToRight(pnExplore);
        } else if (btn == btnNotification) {
            switchToRight(pnNotifications);
        } else if (btn == btnSettings) {
            switchToUp(pnSettings);
        } else if (btn == btnCompose) {
            switchToUp(pnCompose);
        } else if (btn == btnPost) {
            switchToRight(pnFeed);
        } else if (btn == btnNewChat) {
            switchToUp(pnNewChat);
            pnSetChatType.toFront();
        }
    }

    @FXML
    private void forward(ActionEvent event) {
        if (event.getSource() == btnForward)
            ChatListView.getPnForwardInstance().forward(true);
        else
            ChatListView.getPnForwardInstance().forward(false);
        switchToRight(pnChat);
    }

    private void setFeed() {
    }

    private void setExplore() {
        scrollExplore.setContent(new PostListView().withPosts(PostUserConnection.getExplore(User.getCurrentUser())));
    }

    @FXML
    private void logout() throws IOException {
        FXMLLoader loader = App.loadFXML("login");
        Parent root = loader.load();
        App.setRoot(root);
        ((LoginPageController) loader.getController()).setStyle(App.getStyle());
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

    private void switchPanes(AnchorPane pane1, AnchorPane pane2) {
        pane1.setVisible(false);
        pane1.toBack();
        pane2.setVisible(true);
        pane2.toFront();
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
        ChatListView.getPnChatInstance().withChat(ChatUserConnection.getChats(User.getCurrentUser().getId()));
        scrollChat.setContent(ChatListView.getPnChatInstance());
    }

    private void initMessages() {
        MessageListView.getInstance().addAll();
        scrollMsg.setContent(MessageListView.getInstance());
        lblRepliedName.textProperty().bind(MessageListView.getInstance().getRepliedName());
        lblRepliedMsg.textProperty().bind(MessageListView.getInstance().getRepliedMsg());
        btnMsgTop.textProperty().bind(Chat.getCurrentName());
    }

    private void initPosts() {
        scrollPost.setContent(new PostListView().withPosts(PostUserConnection.getFeed(User.getCurrentUser().getId())));
    }

    @FXML
    private void setChatType(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnPrivate) {
            NewChatController.getInstance().setType(NewChatController.getInstance().getChatType("private"));
        } else if (btn == btnChannel) {
            NewChatController.getInstance().setType(NewChatController.getInstance().getChatType("channel"));
        } else if (btn == btnGroup) {
            NewChatController.getInstance().setType(NewChatController.getInstance().getChatType("group"));
        }
        switchPanes(pnSetChatType, pnSetChatName);
    }

    @FXML
    private void confirmChatName(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnConfirmNewChatName)
            if (NewChatController.getInstance().setChatName(txtChatName))
                switchPanes(pnSetChatName, pnSetChatMembers);
            else
                new ErrorNotification("invalid chat name format");
        else if (btn == btnSetNewChatPic) {
            File profilePic = App.getPicChooser().showOpenDialog(App.getScene().getWindow());// TODO: use the file
        }
    }

    @FXML
    private void cancelNewChat() {
        switchToRight(pnChat);
    }

    @FXML
    private void confirmMember(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnAddNewChatMember) {
            if (NewChatController.getInstance().addMember(txtNewMemberName.getText()))
                lstNewChatMembers.getItems().add(txtNewMemberName.getText());
        } else if (btn == btnRemoveNewChatMember) {
            if (NewChatController.getInstance().removeMember(txtNewMemberName.getText()))
                lstNewChatMembers.getItems().remove(txtNewMemberName.getText());
        } else if (btn == btnResetNewChatMembers) {
            NewChatController.getInstance().clearMembers();
            lstNewChatMembers.getItems().clear();
        } else if (btn == btnFinalizeNewGroup) {
            if (!NewChatController.getInstance().finalizeChat()) {
                new ErrorNotification("cannot create an empty group");
                return;
            }
            switchToRight(pnChat);
            switchPanes(pnSetChatMembers, pnSetChatType);
        }
    }

    @FXML
    private void cancelReply() {
        MessageListView.getInstance().clearReply();
    }

    @FXML
    private void msgButton(ActionEvent event) {
        Object o = event.getSource();
        if (!(o instanceof Button))
            return;
        Button btn = (Button) o;
        if (btn == btnMsgSend) {
            StringBuilder sb = new StringBuilder();
            sb.append(txtAMessage.getText());
            ChatController.post(sb, MessageListView.getInstance().getRepliedTo());
            txtAMessage.setText("");
        } else if (btn == btnMsgFile) {
            File selectedFile = App.getPicChooser().showOpenDialog(App.getScene().getWindow());
        }
    }
}
