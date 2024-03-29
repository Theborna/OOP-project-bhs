package com.electro.controllers.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.control.ToggleSwitch;

import com.electro.App;
import com.electro.phase1.controllers.LoginController;
import com.electro.phase1.controllers.RegisterController;
import com.electro.phase1.enums.Security;
import com.electro.phase1.util.Log;
import com.electro.util.ResponsiveHBox;
import com.electro.views.component.ErrorNotification;
import com.electro.views.component.FieldEmptyError;
import com.electro.views.component.InfoNotification;
import animatefx.animation.SlideInRight;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class LoginPageController implements Initializable {

    private static String darkPath = App.class.getResource("css/loginDark.css").toExternalForm(),
            lightPath = App.class.getResource("css/loginLight.css").toExternalForm();

    @FXML
    private Button btnForgot, btnRegister, btnSignIn, btnSignUp, preview, btnGoToSignIn, btnForgotSignIn,
            btnForgotSignUp, btnForgotSignIn1, btnForgotSignUp1;

    @FXML
    private ToggleSwitch tglType, tglVisible, tglTargeted;

    @FXML
    private AnchorPane signIn, signUp, forgot, changePass;

    @FXML
    private DatePicker dateBirth;
    @FXML
    private TextField txtFullName, txtUsername, txtSignUsername, txtEmail, txtSecAns, txtSecurityAns;
    @FXML
    private PasswordField txtPass, txtPassConf, txtSignPassword, txtNewPass, txtNewPassConf;

    @FXML
    private VBox vbox, vboxSign, vboxForgot;

    @FXML
    private ScrollPane scrollSignUp;

    @FXML
    private BorderPane loginPane;

    @FXML
    private Label lblSecurityQ;

    @FXML
    private SplitMenuButton splitBtnSecurity;

    private StringProperty securityQ, chosenSecurityQ;
    private AnchorPane inFront;
    private JMetro metro;

    private File selectedFile;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        selectedFile = null;
        initButton(btnForgot);
        initButton(btnSignIn);
        initButton(btnSignUp);
        initButton(btnRegister);
        initSecurityQuestion();
        securityQ = new SimpleStringProperty();
        lblSecurityQ.textProperty().bind(securityQ.concat("?"));
        // new ProfilePopOver(preview);
        ResponsiveHBox.bind(vboxSign);
        ResponsiveHBox.bind(scrollSignUp);
        ResponsiveHBox.bind(vboxForgot);
        loginPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1,
                    Number arg2) {
                double width = arg1.doubleValue();
                if (width == 0)
                    return;
                if (width < 650)
                    mobile();
                else
                    desktop();
            }

            private void desktop() {
                if (loginPane.getLeft() != null)
                    return;
                Node top = loginPane.getTop();
                loginPane.setTop(null);
                loginPane.setLeft(top);
            }

            private void mobile() {
                if (loginPane.getTop() != null)
                    return;
                Node side = loginPane.getLeft();
                loginPane.setLeft(null);
                loginPane.setTop(side);
            }

        });
    }

    private void initSecurityQuestion() {
        MenuItem items[] = new MenuItem[Security.values().length];
        chosenSecurityQ = new SimpleStringProperty("please choose a question");
        txtSecurityAns.promptTextProperty().bind(chosenSecurityQ.concat(" (answer this)"));
        for (int i = 0; i < items.length; i++) {
            Security s = Security.values()[i];
            items[i] = new MenuItem(s.toString());
            items[i].setOnAction(evt -> chosenSecurityQ.setValue(s.toString()));
        }
        splitBtnSecurity.getItems().addAll(items);
    }

    @FXML
    private void signIn() throws IOException {
        // User.logToUser(, "");
        new FieldEmptyError(txtSignUsername);
        new FieldEmptyError(txtSignPassword);
        if (!LoginController.getInstance().setUsername(txtSignUsername.getText()))
            return;
        if (!LoginController.getInstance().setPassword(txtSignPassword.getText()))
            return;
        if (LoginController.getInstance().logToUser() == null) {
            new ErrorNotification("incorrect username or password");
            return;
        }
        LoginController.getInstance().reset();
        switchToSecondary();
    }

    private void switchToSecondary() throws IOException {
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
        if (b == btnSignUp || b == btnForgotSignUp || b == btnForgotSignUp1) {
            switchTo(signUp);
        } else if (b == btnRegister) {
            if (register()) {
                switchTo(signIn);
            }
        } else if (b == btnGoToSignIn || b == btnForgotSignIn || b == btnForgotSignIn1) {
            switchTo(signIn);
        } else if (b == btnForgot) {
            forgot();
        }
    }

    private void forgot() {
        String username, question;
        new FieldEmptyError(txtSignUsername);
        if (!LoginController.getInstance().setUsername(username = txtSignUsername.getText()))
            return;
        if ((question = LoginController.getInstance().securityQuestion(username)) == null) {
            new ErrorNotification("user does not exit");
            return;
        }
        securityQ.set(question);
        switchTo(forgot);
    }

    @FXML
    private void browsePfp() {
        selectedFile = App.getPicChooser().showOpenDialog(App.getScene().getWindow());
    }

    private boolean register() {
        RegisterController controller = new RegisterController();
        new FieldEmptyError(txtFullName);
        new FieldEmptyError(txtUsername);
        new FieldEmptyError(txtPass);
        new FieldEmptyError(txtPassConf);
        new FieldEmptyError(txtEmail);
        controller.setPfp(selectedFile);
        Security s;
        controller.setSecurityQ(s = Security.getQuestion(chosenSecurityQ.get()));
        if (s == null) {
            new ErrorNotification("please choose a security question");
            return false;
        }
        String username = txtUsername.getText(), password = txtPass.getText(),
                passwordConf = txtPassConf.getText(), email = txtEmail.getText();
        boolean b = controller.setUsername(username) && controller.setPassword(password) && controller.setEmail(email)
                && controller.getSecurityQuestion(txtSecurityAns.getText())
                && controller.setName(txtFullName.getText());
        System.out.println(chosenSecurityQ.get());
        System.out.println(txtSecurityAns.getText());
        if (!password.equals(passwordConf)) {
            if (passwordConf.length() != 0)
                new ErrorNotification("password not the same as confirm password");
            return false;
        }
        if (b) {
            Boolean isNormal = tglType.isSelected();
            Boolean isPublic = tglVisible.isSelected();
            String birthDate = dateBirth.getTypeSelector();
            System.out.println(isNormal + " " + isPublic + " " + birthDate);
            controller.makeAccount(isPublic, isNormal,
                    Date.from(dateBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            // b = false;
        }
        return b;
    }

    // @FXML
    // private void toggleAction(ActionEvent event) {
    // // Object o = event.getSource();
    // // if (!(o instanceof ToggleSwitch))
    // // return;
    // // ToggleSwitch b = (ToggleSwitch) o;
    // // if (b == tglVisible) {
    // // if (tglVisible.isSelected())
    // // tglVisible.setText("visible");
    // // else
    // // tglVisible.setText("private");
    // // } else if (b == tglType) {
    // // if (tglType.isSelected())
    // // tglType.setText("normal");
    // // else
    // // tglType.setText("business");
    // // }
    // }

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

    @FXML
    private void confirmSecurity() {
        new FieldEmptyError(txtSecAns);
        if (txtSecAns.getText().length() < 1)
            return;
        String pass;
        if ((pass = LoginController.getInstance().isCorrectSecure(txtSecAns.getText())) == null) {
            new ErrorNotification("incorrect answer");
            return;
        }
        new InfoNotification("change ur password");
        switchTo(changePass);
    }

    @FXML
    private void changePass() {
        new FieldEmptyError(txtPass);
        new FieldEmptyError(txtPassConf);
        String pass;
        if ((pass = LoginController.getInstance().getPassword(txtPass.getText())) == null) {
            new ErrorNotification("invalid password format");
        } else {
            return;
        }
    }
}
