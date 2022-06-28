
package com.project.view.general;

import com.project.controllers.FeedController;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

public class FeedView implements View {
    private static FeedView instance;
    private FeedController controller;

    private FeedView() {
        controller = new FeedController();
        controller.addAll(User.getCurrentUser().getPosts());
        controller.getCurrentPost();
    }

    public static FeedView getInstance() {
        if (instance == null)
            instance = new FeedView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        controller.getCurrentPost().show();
        printSelections("scroll up", "scroll down", "show post -id", "top", "up vote", "down vote", "comment");
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public FeedController getController() {
        return controller;
    }

}
