package com.project.controllers;

import com.project.App;
import com.project.models.node.user.User;
import com.project.view.general.CreatePostView;
import com.project.view.general.FeedView;

public class SecondaryController implements Controller {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        System.out.println("kos mikham with input: " + input);
        switch (input) {
            case "feed":
                App.setView(new FeedView());
                break;
            case "new post":
                App.setView(new CreatePostView().setUser(user));
                break;
            default:
                break;
        }
    }

}
