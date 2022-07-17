package com.project.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.project.App;
import com.project.models.node.Post;
import com.project.util.StdColor;
import com.project.util.exception.changeViewException;
import com.project.view.general.CreatePostView;
import com.project.view.model.PageView;
import com.project.view.model.PostView;
import static com.project.util.StdOut.*;

public class FeedController implements ListController<PostView> {
    private List<PostView> postViews = new ArrayList<PostView>();
    private PostView currentPost;
    private int current;

    @Override
    public void parse(String input) throws changeViewException {
        input = input.toLowerCase().trim();
        switch (input) {
            case "n":
            case "next":
            case "scroll down":
            case "down":
            case "d":
                currentPost = postViews.get((++current < postViews.size()) ? current : (current = 0));
                break;
            case "l":
            case "last":
            case "scroll up":
            case "up":
            case "u":
                currentPost = postViews.get((--current >= 0) ? current : (current = 0));
                break;
            case "t":
            case "top":
                currentPost = postViews.get(0);
                break;
            case "show -page":
                App.setView(PageView.getInstance().setUser(currentPost.getPost().getSender()));
                break;
            case "new post":
                App.setView(new CreatePostView().setInReplyTo(null));
                break;
            case "comment":
                App.setView(new CreatePostView().setInReplyTo(currentPost.getPost().getId()));
                break;
            case "help":
                help();
                break;
            default:
                printError("no such command");
                break;
        }
    }

    public PostView getCurrent() {
        if (currentPost == null) {
            if (postViews.size() != 0)
                currentPost = postViews.get(0);
        }
        return currentPost;
    }

    public List<PostView> getChildren() {
        return postViews;
    }

    public void clear() {
        postViews.clear();
    }

    public void addAll(Collection<Post> posts) {
        postViews.addAll(posts.stream().map(PostView::new).toList());
    }

    @Override
    public void help() {
        rule('*');
        print("next, n, down, d, scroll down:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls down to view the next post");
        print("last, l, up, u, scroll up:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls up to view the last post");
        print("top, t:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls to the top of the feed");
        // print("show -all, all:", StdColor.MAGENTA_UNDERLINED);
        // println(" shows all chat items at once");
        print("help:", StdColor.MAGENTA_UNDERLINED);
        println(" brings up the help window");
        rule('*');
    }
}
