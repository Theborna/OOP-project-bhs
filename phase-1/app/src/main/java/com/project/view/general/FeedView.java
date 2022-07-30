package com.project.view.general;

import com.project.controllers.FeedController;
import com.project.models.connection.Like;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.util.Set;

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
        controller.addAll(PostUserConnection.getFeed(User.getCurrentUser().getId()));
    }

    public static FeedView getInstance() {
//        if (instance == null)
            instance = new FeedView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        if(controller.getCurrent() != null) {
            controller.getCurrent().show();
            printCommands();
        } else
            println("no posts to show",StdColor.CYAN);
        prompt("enter next command");
        String input = StdIn.nextLine();
        controller.parse(input);
        showLikes(input);
    }

    private void showLikes(String input) {
        switch (controller.showLikes(input)) {
            case 0:
                break;
            case 1:
                printError("you are not the author");
                break;
            case 2:
                showLikes(controller.getCurrent().getPost());
                break;
            default:
                break;
        }
    }

    private void showLikes(Post post) {
        Set<User> likes = Like.getUsers(post);
        for (User user : likes) {
            print(user.toString(), StdColor.WHITE_BOLD_BRIGHT);
            print('|');
        }
    }

    protected void printCommands() {
        printSelections("next", "last", "top", "like", "dislike",
                "new post", "comment", "show -likes", "show -comments", "show -page");
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
    @Override
    public void reset() {
        instance = null;
    }
}
