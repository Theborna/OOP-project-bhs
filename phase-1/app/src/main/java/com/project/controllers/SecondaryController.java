package com.project.controllers;

import com.project.App;
import com.project.models.node.user.User;
import com.project.view.general.ChatListView;
import com.project.view.general.ExploreView;
import com.project.view.general.FeedView;
import com.project.view.general.SettingsView;
import com.project.view.model.PageView;
import com.project.view.model.SelfPageView;

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
            case "page":
                App.setView(SelfPageView.getInstance().refresh());
                break;
            case "explore":
                App.setView(ExploreView.getInstance());
                break;
            case "settings":
                App.setView(new SettingsView());
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
