package com.project.controllers;

import com.project.App;
import com.project.models.node.user.User;
import com.project.view.general.CreatePostView;
import com.project.view.general.FeedView;
import com.project.view.model.PostView;

public class SecondaryController implements Controller {

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        System.out.println("kos mikham with input: " + input);
        switch (input) {
            case "feed":
                FeedView.getInstance().getChildren()
                        .addAll(User.getCurrentUser().getPosts().stream().map(PostView::new).toList());
                App.setView(FeedView.getInstance());
                break;
            case "new post":
                App.setView(new CreatePostView());
                break;
            default:
                break;
        }
    }

}
