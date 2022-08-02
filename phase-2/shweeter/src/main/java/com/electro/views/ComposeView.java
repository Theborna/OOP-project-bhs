package com.electro.views;

import com.electro.controllers.views.ComposeController;
import com.electro.phase1.models.node.post.Post;

public class ComposeView extends inPane {

    private ComposeController controller;

    public ComposeView() {
        super();
        controller = getController("compose");
    }

    public ComposeView withPost(Post withPost) {
        controller.setPost(withPost);
        return this;
    }

    public void setOnFinished(Runnable run) {
        controller.finishedProperty().addListener((a, old, niu) -> {
            if (niu)
                run.run();
        });
    }

}
