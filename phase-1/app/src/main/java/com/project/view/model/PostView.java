package com.project.view.model;

import com.project.controllers.Controller;
import com.project.models.node.Post;
import com.project.view.View;

public class PostView implements View {

    private Post post;

    public PostView(Post post) {
        this.post = post;
    }

    @Override
    public void show() {

    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }                            

}
