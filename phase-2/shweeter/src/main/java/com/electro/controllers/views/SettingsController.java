package com.electro.controllers.views;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.ToggleSwitch;

import com.electro.App;
import com.electro.database.MediaDB;
import com.electro.phase1.AppRegex;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Media;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.crypt;
import com.electro.views.component.ErrorNotification;
import com.electro.views.component.FieldEmptyError;
import com.electro.views.component.InfoNotification;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class SettingsController implements Initializable {
    private User user;
    private StringProperty username;
    private StringProperty password;
    private StringProperty name;
    private StringProperty email;
    private String bio;
    private BooleanProperty visible;
    private Media pfp;

    @FXML
    private Button btnConfirm;

    @FXML
    private AnchorPane pnSettings;

    @FXML
    private TextField txtEmail, txtName, txtPassword, txtUsername;

    @FXML
    private ToggleSwitch tglPriv, tglType;

    public SettingsController withUser(User currentUser) {
        this.user = currentUser;
        return this;
    }

    public void init() {
        username.set(user.getUsername());
        email.set(user.getEmail());
        // password.set(user.getPassword());
        name.set(user.getName());
        visible.set(user.isPublic());
        pfp = user.getProfilePhoto();
    }

    public void confirm() {// TODO : complete these
        user.setName(name.get());
        if (password.get() != null)
            user.setPassword(crypt.encryptedString(password.get() + user.getSalt()));
        user.setUsername(username.get());
        user.setPublic(visible.get());
        try {
            pfp.setId(MediaDB.newMedia(pfp));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setProfilePhoto(pfp);
        user.sendToDB();
        new InfoNotification("changes made successfully");
    }

    public boolean SetEmail(String email) {
        if (!AppRegex.EMAIL.matches(email))
            return false;
        this.email.set(email);
        return true;
    }

    public boolean setUserName(String username) {
        if (!AppRegex.USERNAME.matches(username))
            return false;
        this.username.set(username);
        return true;
    }

    public boolean setPassword(String password) {
        if (password == null)
            return true;
        if (!AppRegex.PASSWORD.matches(password))
            return false;
        this.password.set(password);
        return true;
    }

    public boolean setFullName(String name) {
        if (!AppRegex.FULL_NAME.matches(name))
            return false;
        this.name.set(name);
        return true;
    }

    public boolean setBio(String bio) {
        if (!AppRegex.BIOGRAPHY.matches(bio))
            return false;
        this.bio = bio;
        return true;
    }

    public boolean setVisibility(String visibility) {
        visibility = visibility.toLowerCase().trim();
        if (!visibility.equals("public") && !visibility.equals("private"))
            return false;
        this.visible.set(visibility.equals("public"));
        return true;
    }

    @FXML
    private void confirmChanges() {
        new FieldEmptyError(txtName);
        new FieldEmptyError(txtPassword);
        new FieldEmptyError(txtEmail);
        new FieldEmptyError(txtUsername);
        if (!setFullName(name.get())) {
            new ErrorNotification("invalid username format");
        } else if (!setPassword(password.get())) {
            new ErrorNotification("invalid password format");
        } else if (!setUserName(username.get())) {
            new ErrorNotification("invalid username format");
        } else if (!SetEmail(email.get())) {
            new ErrorNotification("invalid email format");
        } else
            confirm();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name = new SimpleStringProperty();
        password = new SimpleStringProperty();
        email = new SimpleStringProperty();
        username = new SimpleStringProperty();
        visible = new SimpleBooleanProperty();
        txtName.textProperty().bindBidirectional(name);
        txtPassword.textProperty().bindBidirectional(password);
        txtEmail.textProperty().bindBidirectional(email);
        txtUsername.textProperty().bindBidirectional(username);
        visible.bindBidirectional(tglPriv.selectedProperty());
        visible.addListener((a, old, niu) -> System.out.println(niu));
    }

    @FXML
    private void choosePic() {
        pfp = new ImageNode(App.getPicChooser().showOpenDialog(App.getScene().getWindow()).getPath());
    }
}
