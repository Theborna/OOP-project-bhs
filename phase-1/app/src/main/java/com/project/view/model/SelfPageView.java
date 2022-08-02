package com.project.view.model;

import com.project.controllers.SelfPageController;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.user.BusinessUser;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

public class SelfPageView extends PageView {
    private static SelfPageView instance;

    protected SelfPageView() {
        controller = new SelfPageController();
        user = User.getCurrentUser();
        controller.addAll(PostUserConnection.getPosts(User.getCurrentUser().getId()));
        controller.getCurrent();
    }

    public static SelfPageView getInstance() {
        // if (instance == null)
        instance = new SelfPageView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        if (((SelfPageController) controller).isNeedInfo())
            gaveBasic = false;
        if (!gaveBasic) {
            info();
            int ans = 0;
            do {
                println("");
                printSelections("Y", "N");
                prompt("continue?");
            } while ((ans = ((SelfPageController) controller).isContinue(StdIn.nextLine())) == 3);
            if (ans == 2)
                return;
            gaveBasic = true;
        }
        super.showSuper();
    }

    @Override
    protected void printCommands() {
        if (User.getCurrentUser() instanceof BusinessUser)
            printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "info", "settings",
                    "comment", "show -comments", "show -stats");
        else
            printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "info", "settings",
                    "comment", "show -comments");
    }

    @Override
    public PageView setUser(User user) {
        return instance;
    }

    public View refresh() {
        user = User.getCurrentUser();
        return this;
    }

}
