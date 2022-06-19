package com.project.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.project.models.node.Post;
import com.project.view.model.PostView;
import static com.project.util.StdOut.*;

public class FeedController implements Controller {
    private List<PostView> postViews = new ArrayList<PostView>();
    private PostView currentPost;
    private int current;

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        switch (input) {
            case "d":
            case "down":
            case "scroll down":
                currentPost = postViews.get((++current < postViews.size()) ? current : (current = 0));
                break;
            case "u":
            case "up":
            case "scroll up":
                currentPost = postViews.get((--current >= 0) ? current : (current = 0));
                break;
            case "t":
            case "top":
            case "go to top":
                currentPost = postViews.get(0);
            default:
                break;
        }
    }

    public PostView getCurrentPost() {
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
        rule('*');
    }
}
