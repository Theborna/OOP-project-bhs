package com.view.general;

import com.controllers.FeedController;
import com.models.connection.PostUserConnection;
import com.models.node.user.User;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;
import static com.util.StdOut.printSelections;
import static com.util.StdOut.prompt;

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
        printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "show -page");
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
