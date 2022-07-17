package com.project.controllers;

import com.project.App;
import com.project.view.general.ChatListView;
import com.project.view.general.CreatePostView;
import com.project.view.general.FeedView;
import static com.project.util.StdOut.*;

public class SecondaryController implements Controller {

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        switch (input) {
            case "feed":
                App.setView(FeedView.getInstance());
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
