package com.project.view.general;

import com.project.controllers.FeedController;
import com.project.models.connection.Like;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.format;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.util.HashSet;
import java.util.Set;

public class FeedView implements View {
    private static FeedView instance;
    protected FeedController controller;

    public FeedView() {
    }

    public void getFeed() {
        controller = new FeedController();
        controller.clear();
        controller.addAll(PostUserConnection.getFeed(User.getCurrentUser().getId()));
        controller.getCurrent();
    }

    public static FeedView getInstance() {
        // if (instance == null)
        instance = new FeedView();
        instance.getFeed();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        if (controller.getCurrent() != null) {
            controller.getCurrent().show();
            printCommands();
        } else
            println("no posts to show", StdColor.CYAN);
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
        Set<Like> likes = Like.getUsers(post);
        int likesCnt = 0, dislikeCnt = 0;
        for (Like like : likes) {
            if (like.getValue() == 0) {
                continue;
            } else if (like.getValue() == 1) {
                likesCnt++;
                print(like.getObj2().getName() + " :", StdColor.GREEN);
            } else if (like.getValue() == -1) {
                dislikeCnt++;
                print(like.getObj2().getName() + " :", StdColor.RED);
            }
            println("@ " + format.SimpleDate(like.getLastModifiedDate()));
        }
        println("\ntotal likes: " + likesCnt + ", total dislikes: " + dislikeCnt + ", total views: " + likes.size());
    }

    protected void printCommands() {
        if (!controller.getCurrent().getPost().getSender().equals(User.getCurrentUser()))
            printSelections("next", "last", "top", "like", "dislike",
                    "new post", "comment", "show -likes", "show -comments", "show -page");
        else
            printSelections("next", "last", "top", "like", "dislike",
                    "new post", "comment", "show -likes", "show -comments", "show -page", "delete");
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
