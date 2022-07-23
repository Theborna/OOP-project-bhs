package com.electro.phase1.controllers;


import com.electro.phase1.models.node.user.User;


import static com.electro.phase1.util.StdOut.*;

public class SecondaryController implements Controller {

    // @Override
    // public void parse(String input) {
    //     // input = input.toLowerCase().trim();
    //     // switch (input) {
    //     //     case "feed":
    //     //         App.setView(FeedView.getInstance());
    //     //         break;
    //     //     case "chat":
    //     //         App.setView(ChatListView.getInstance());
    //     //         break;
    //     //     case "page":
    //     //         App.setView(SelfPageView.getInstance().refresh());
    //     //         break;
    //     //     case "explore":
    //     //         App.setView(ExploreView.getInstance());
    //     //         break;
    //     //     case "help":
    //     //         help();
    //     //         break;
    //     //     default:
    //     //         break;
    //     // }
    // }

    @Override
    public void help() {
        // TODO : implement help
        rule('*');
        rule('*');
    }

}
