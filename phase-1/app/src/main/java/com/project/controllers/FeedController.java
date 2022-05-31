package com.project.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.project.LimitedList;
import com.project.models.node.Post;
import com.project.view.model.PostView;

public class FeedController implements Controller {
    private List<PostView> postViews = new ArrayList<PostView>();
    private PostView currentPost;
    private int current;

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        switch (input) {
            case "down":
            case "scroll down":
                currentPost = postViews.get((++current < postViews.size()) ? current : (current = 0));
                break;
            case "up":
            case "scroll up":
                currentPost = postViews.get((--current >= 0) ? current : (current = 0));
                break;
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
}
