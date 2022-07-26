package com.project.view.general;

import com.project.models.node.post.Post;
import com.project.util.exception.changeViewException;

public class CommentView extends FeedView {
    private Post post;

    public CommentView withPost(Post post) {
        this.post = post;
        getFeed();
        return this;
    }

    @Override
    public void getFeed() {
        if (post == null)
            return;
        controller.clear();
        controller.addAll(post.getComments());
        controller.getCurrent();
    }

    @Override
    public void show() throws changeViewException {
        super.show();
    }
}
