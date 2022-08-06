package com.electro.controllers.views;

import java.io.File;
import java.io.IOException;

import com.electro.App;
import com.electro.controllers.components.postController;
import com.electro.phase1.AppRegex;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;
import com.electro.views.FileView;
import com.electro.views.component.ErrorNotification;
import com.electro.views.component.FieldEmptyError;
import com.electro.views.component.InfoNotification;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ComposeController {

    @FXML
    private Button btnDelete, btnFile, btnPost;

    @FXML
    private AnchorPane pnCompose;

    @FXML
    private BorderPane bpMain;

    @FXML
    private TextArea txtPost;

    private BooleanProperty finishedProperty;
    private File file;
    private Post inReply;

    @FXML
    void btnHandle(ActionEvent event) {
        Object o = event.getSource();
        if (!(o instanceof Button))
            return;
        Button btn = (Button) o;
        if (o == btnPost)
            post();
        else if (o == btnDelete)
            delete();
        else if (o == btnFile)
            file();
    }

    private void file() {
        file = App.getPicChooser().showOpenDialog(App.getScene().getWindow());
        System.out.println(file.toPath().toAbsolutePath().toString());
        bpMain.setBottom(new FileView(new ImageNode(file.toPath().toAbsolutePath().toString())));
    }

    private void delete() {
        finishedProperty.set(true);
        new InfoNotification("post canceled successfully");
    }

    private void post() {
        String text = txtPost.getText();
        if (text.length() == 0)
            new ErrorNotification("post cannot be empty");
        // } else if (AppRegex.P/ )
        finishedProperty.set(true);
        User.getCurrentUser().Post(text, inReply);
        new InfoNotification("post sent successfully");
    }

    public void setPost(Post withPost) {
        if (finishedProperty == null)
            finishedProperty = new SimpleBooleanProperty(false);
        finishedProperty.set(false);
        file = null;
        txtPost.setText(null);
        if (withPost == null) {
            inReply = null;
            bpMain.setTop(null);
            return;
        }
        inReply = withPost;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("components/post.fxml"));
        Node node;
        try {
            node = loader.load();
            postController controller = loader.getController();
            Platform.runLater(() -> {
                controller.initialize(withPost);
                bpMain.setTop(node);
                node.setDisable(true);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BooleanProperty finishedProperty() {
        if (finishedProperty == null)
            finishedProperty = new SimpleBooleanProperty(false);
        return finishedProperty;
    }

}
