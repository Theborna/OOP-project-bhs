package com.project.view.general;

import com.project.controllers.FeedController;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import com.project.view.model.PostView;

import static com.project.util.StdOut.*;

public class FeedView implements View {
    private static FeedView instance;
    protected FeedController controller;

    protected FeedView() {
        controller = new FeedController();
        getFeed();
        controller.getCurrent();
    }

    public void getFeed() {
        controller.clear();
        controller.addAll(PostUserConnection.getFeed(User.getCurrentUser()));
    }

    public static FeedView getInstance() {
        if (instance == null)
            instance = new FeedView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        controller.getCurrent().show();
        printCommands();
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    protected void printCommands() {
        printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "show -page",
                "new post", "comment");
    }

    @Override
    @SuppressWarnings("unchecked")
    public FeedController getController() {
        return controller;
    }

    // @Override
    // public <T extends Controller> T getController() {
    // return controller;
    // }

}
