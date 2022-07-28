package com.electro.controllers.components;

import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.post.PromotedPost;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.format;
import com.electro.views.component.ProfilePopOver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class postController {

    @FXML
    private Button btnComment, btnDislike, btnLike, btnShare;

    @FXML
    private Text content;

    @FXML
    private ImageView ivProfilePic;

    @FXML
    private Label lblLikes, lblName, lblDate, lblAdvertisement;

    private Post post;

    public void initialize(Post post) {
        this.post = post;
        content.setText(post.getText());
        lblName.setText(post.getSender().getUsername());
        lblLikes.setText(String.valueOf(post.getLikes()));
        lblDate.setText(format.SimpleDate(post.getCreationDate()));
        lblAdvertisement.visibleProperty().set(post instanceof PromotedPost);
        new ProfilePopOver(ivProfilePic, post.getSender());
    }

    @FXML
    private void btnHandle(ActionEvent event) {

        Object o = event.getSource();
        if (!(o instanceof Button))
            return;
        Button btn = (Button) o;
        if (btn == btnLike)
            like();
        else if (btn == btnDislike)
            dislike();
        else if (btn == btnComment)
            comment();
        else if (btn == btnShare)
            share();
    }

    private void share() {
    }

    private void comment() {
    }

    private void dislike() {
        User.getCurrentUser().dislike(post);
    }

    private void like() {
        User.getCurrentUser().like(post);
    }

}
