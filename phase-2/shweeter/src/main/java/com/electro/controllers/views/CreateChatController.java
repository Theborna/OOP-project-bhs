package com.electro.controllers.views;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.textfield.CustomTextField;

import com.electro.App;
import com.electro.phase1.controllers.NewChatController;
import com.electro.phase1.enums.ChatPermission;
import com.electro.views.component.ErrorNotification;

public class CreateChatController implements Initializable {

    @FXML
    private Button btnAddNewChatMember, btnChannel, btnConfirmNewChatName, btnGroup, btnPrivate,
            btnRemoveNewChatMember, btnResetNewChatMembers, btnSetNewChatPic, btnConfirm;

    @FXML
    private ListView<String> lstMembers, lstPermissions;

    @FXML
    private AnchorPane pnSetChatMembers, pnSetChatName, pnSetChatType;

    private AnchorPane onTop;

    @FXML
    private CustomTextField txtChatName, txtChatLinkId;

    @FXML
    private TextField txtNewMemberName;

    private BooleanProperty finishedProperty;
    private ObservableList<String> members, permissions;

    private NewChatController controller;
    private ContextMenu context;

    @FXML
    private void cancelNewChat() {
        finishedProperty.setValue(true);
        initialize(null, null);
    }

    @FXML
    private void confirmChatName(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnConfirmNewChatName) {
            if (controller.setChatName(txtChatName)) {
                if (controller.setChatLinkId(txtChatLinkId))
                    switchPanes(pnSetChatName, pnSetChatMembers);
                else
                    new ErrorNotification("invalid chat id format");
            } else
                new ErrorNotification("invalid chat name format");
        } else if (btn == btnSetNewChatPic) {
            File profilePic = App.getPicChooser().showOpenDialog(App.getScene().getWindow());// TODO: use the file
        }
    }

    @FXML
    private void confirmMember(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        String member = txtNewMemberName.getText();
        if (btn == btnAddNewChatMember) {
            if (controller.addMember(member)) {
                members.add(member);
                permissions.add(controller.getPermissions().get(member).toString().toLowerCase());
            }
        } else if (btn == btnRemoveNewChatMember) {
            if (controller.removeMember(member)) {
                int i = members.indexOf(member);
                members.remove(i);
                permissions.remove(i);
            }
        } else if (btn == btnResetNewChatMembers) {
            controller.clearMembers();
            setInitial();
        } else if (btn == btnConfirm) {
            if (!controller.makeChat()) {
                new ErrorNotification("cannot create an empty group");
                return;
            }
            // switchToRight(pnChat);
            switchPanes(pnSetChatMembers, pnSetChatType);
        }
    }

    @FXML
    private void setChatType(ActionEvent event) {
        Object obj = event.getSource();
        if (!(obj instanceof Button))
            return;
        Button btn = (Button) obj;
        if (btn == btnPrivate) {
            controller.setType(controller.getChatType("private"));
            txtChatLinkId.setDisable(true);
        } else if (btn == btnChannel) {
            controller.setType(controller.getChatType("channel"));
        } else if (btn == btnGroup) {
            controller.setType(controller.getChatType("group"));
        }
        switchPanes(pnSetChatType, pnSetChatName);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        controller = new NewChatController();
        if (members == null)
            members = FXCollections.observableArrayList();
        if (permissions == null)
            permissions = FXCollections.observableArrayList();
        lstMembers.setItems(members);
        lstPermissions.setItems(permissions);
        pnSetChatType.toFront();
        pnSetChatType.setVisible(true);
        if (onTop != null)
            onTop.setVisible(false);
        onTop = pnSetChatType;
        if (finishedProperty == null)
            finishedProperty = new SimpleBooleanProperty();
        finishedProperty.setValue(false);
        setContext();
        // initial values
        setInitial();
    }

    private void setContext() {
        Map<MenuItem, ChatPermission> contextItems = new LinkedHashMap<>();
        for (ChatPermission permission : ChatPermission.values()) {
            MenuItem item = new MenuItem(permission.name().toLowerCase());
            contextItems.put(item, permission);
            item.setOnAction(evt -> {
                int index = lstPermissions.getSelectionModel().selectedIndexProperty().get();
                if (index == -1)
                    return;
                System.out.println(members.get(index) + ":" + permissions.get(index) + " -> " + contextItems.get(item));
                if (controller.setPermission(members.get(index), contextItems.get(item))) {
                    permissions.set(index, contextItems.get(item).name().toLowerCase());
                } else
                    new ErrorNotification("cannot change permissions for self");
            });
        }
        context = new ContextMenu(contextItems.keySet().toArray(new MenuItem[contextItems.size()]));
        lstPermissions.setContextMenu(context);
    }

    /**
     * adding initial values for chat settings type stuff
     */
    private void setInitial() {
        members.clear();
        permissions.clear();
        txtNewMemberName.clear();
        txtChatName.clear();
        txtChatLinkId.setDisable(false);
        txtChatLinkId.clear();
        txtChatName.setText(controller.getName());
        txtChatLinkId.setText(controller.getLinkID());
        members.addAll(controller.getInitialMembers().values());
        permissions.addAll(controller.getPermissions().values().stream().map(i -> i.toString().toLowerCase())
                .collect(Collectors.toList()));
    }

    private void switchPanes(AnchorPane pane1, AnchorPane pane2) {
        pane1.setVisible(false);
        pane1.toBack();
        pane2.setVisible(true);
        pane2.toFront();
        onTop = pane2;
    }

    public BooleanProperty getFinishedProperty() {
        return finishedProperty;
    }

}
