package com.project.view.general;

import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import static com.project.util.StdOut.*;

import com.project.App;
import com.project.controllers.Controller;
import com.project.controllers.CreatePostController;

public class CreatePostView implements View {

    private StringBuilder postText = new StringBuilder();
    private CreatePostController controller = new CreatePostController();
    private Long inReplyTo = null;

    @Override
    public void show() throws changeViewException {
        while (true) {
            prompt("What's happening?");
            print("\n");
            if (controller.getPostText(postText))
                break;
        }
        System.out.println(postText);
        // TODO: add more stuff to posts
//        Post post = new Post(postText.toString());
        User.getCurrentUser().Post(postText.toString());
        inReplyTo = null;
        App.setView(App.lastView());
    }

    public CreatePostView inReplyTo(Long inReplyTo) {
        this.inReplyTo = inReplyTo;
        return this;
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    @Override
    public void reset() {
    }
}
