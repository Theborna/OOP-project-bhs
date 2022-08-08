package com.electro.controllers.components;

import java.util.Set;
import java.util.function.Consumer;

import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.post.PromotedPost;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.format;
import com.electro.views.FileView;
import com.electro.views.PostListView;
import com.electro.views.component.PostStatsPopOver;
import com.electro.views.component.ProfilePopOver;

import animatefx.animation.BounceIn;
import animatefx.animation.BounceOut;
import animatefx.animation.JackInTheBox;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class postController {

    @FXML
    private Button btnComment, btnDislike, btnLike, btnShare, btnAdvertisement;

    @FXML
    private Text content;

    @FXML
    private ImageView ivProfilePic;

    @FXML
    private Label lblLikes, lblName, lblDate, lblAdvertisement, lblReply;

    @FXML
    private BorderPane bpMain, bpPost, bpMsg;

    @FXML
    private AnchorPane pnComments;

    @FXML
    private VBox vboxComments;

    private boolean loadedComments;

    private Post post;

    private static Consumer<Post> commentAction;

    public void initialize(Post post) {
        this.post = post;
        content.setText(post.getText());
        lblName.setText(post.getSender().getUsername());
        lblLikes.setText(String.valueOf(post.getLikes()));
        lblDate.setText(format.SimpleDate(post.getCreationDate()));
        btnComment.setText(String.valueOf(post.getCommentsCount()));
        lblAdvertisement.visibleProperty().set(post instanceof PromotedPost);
        new ProfilePopOver(ivProfilePic, post.getSender());
        advertisement();
        loadedComments = false;
        commentHandler();
        bpMain.setOnMouseEntered(evt -> User.getCurrentUser().view(post));
        if (post.getRepliedPost() == null)
            bpPost.setTop(null);
        else
            lblReply.setText("in reply to " + post.getRepliedPost().getSender().getUsername());
        setPfp();
        setMedia();
    }

    private void setPfp() {
        Image img = ((ImageNode) post.getSender().getProfilePhoto()).getImage();
        double height = ivProfilePic.getFitHeight();
        ivProfilePic.setImage(img = ((ImageNode) post.getSender().getProfilePhoto())
                .getImage(height * img.getWidth() / img.getHeight(), height));
        ivProfilePic.setClip(
                new Circle(ivProfilePic.getFitWidth() / 2, ivProfilePic.getFitHeight() / 2,
                        ivProfilePic.getFitHeight() / 2));
    }

    private void setMedia() {
        if(post.getMd() == null)
            bpMsg.setBottom(null);
        else
            bpMsg.setBottom(new FileView(post.getMd()));
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
        else if (btn == btnShare)
            share();
        else if (btn == btnComment)
            commentAction.accept(post);
        // else if (btn == btnAdvertisement)
        // advertisement();
    }

    private void advertisement() {
        btnAdvertisement.visibleProperty()
                .set(post.getSender().equals(User.getCurrentUser()) && (post instanceof PromotedPost));
        if (post instanceof PromotedPost)
            new PostStatsPopOver(btnAdvertisement, (PromotedPost) post);
    }

    private void share() {
    }

    private void dislike() {
        User.getCurrentUser().dislike(post);
    }

    private void like() {
        User.getCurrentUser().like(post);
    }

    private void commentHandler() {
        bpMain.setBottom(null);
        bpPost.setOnMouseClicked(evt -> {
            if (!loadedComments)
                loadComments();
            if (bpMain.getBottom() == null) {
                new BounceIn(pnComments).play();
                bpMain.setBottom(pnComments);
            } else {
                BounceOut bounceOut = new BounceOut(pnComments);
                bounceOut.play();
                bounceOut.setOnFinished(_evt -> bpMain.setBottom(null));
            }
        });
    }

    private void loadComments() {
        loadedComments = true;
        Set<Post> comments = post.getComments();
        if (comments.size() == 0)
            return;
        vboxComments.getChildren().clear();
        new PostListView().withPosts(comments).getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> arg0) {
                vboxComments.getChildren().add(arg0.getList().get(arg0.getList().size() - 1));
            }
        });
        vboxComments.setSpacing(10);
    }

    public static void setCommentAction(Consumer<Post> commentAction) {
        postController.commentAction = commentAction;
    }
}
