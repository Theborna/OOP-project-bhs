package com.project.controllers;

import com.project.App;
import com.project.models.node.user.User;
import com.project.util.exception.changeViewException;
import com.project.view.model.PageView;

import static com.project.util.StdOut.*;

public class PageController extends FeedController {

    private boolean needInfo;
    private User user;
    private boolean follows;

    @Override
    public void parse(String input) throws changeViewException {
        input = input.toLowerCase().trim();
        if (needInfo)
            needInfo = false;
        switch (input) {
            case "info":
                needInfo = true;
                break;
            case "follow":
                if (follows)
                    printError("already following!");
                else {
                    User.getCurrentUser().follow(user);
                    follows = true;
                }
                break;
            case "un follow":
            case "un":
                if (!follows)
                    printError("not following!");
                else {
                    User.getCurrentUser().unfollow(user);
                    follows = false;
                }
                break;
            default:
                super.parse(input);
                break;
        }
    }

    public int isContinue(String input) throws changeViewException {
        input = input.toLowerCase().trim();
        switch (input) {
            case "yes":
            case "y":
                return 1;
            case "no":
            case "n":
                App.setView(App.lastView());
                return 2;
            default:
                return 3;
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNeedInfo() {
        return needInfo;
    }

    public boolean isFollows() {
        return follows;
    }

    public void setFollows(boolean follows) {
        this.follows = follows;
    }
}
