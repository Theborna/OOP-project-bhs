package com.controllers;

import com.App;
import com.view.general.ChatListView;
import com.view.general.CreatePostView;
import com.view.general.FeedView;

import static com.util.StdOut.rule;

public class SecondaryController implements Controller {

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        System.out.println("kos mikham with input: " + input);
        switch (input) {
            case "feed":
                App.setView(FeedView.getInstance());
                break;
            case "new post":
                App.setView(new CreatePostView());
                break;
            case "chat":
                App.setView(ChatListView.getInstance());
                break;
            case "help":
                help();
                break;
            default:
                break;
        }
    }

    @Override
    public void help() {
        // TODO : implement help
        rule('*');
        rule('*');
    }

}
