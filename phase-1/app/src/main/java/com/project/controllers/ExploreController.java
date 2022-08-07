package com.project.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.project.models.node.user.User;
import com.project.util.Suggestion;
import com.project.view.model.UserView;

public class ExploreController extends FeedController {
    private List<UserView> userViews = new ArrayList<UserView>();

    @Override
    protected void dislike() {
        super.dislike();
        Suggestion.likedSuggestedPost(User.getCurrentUser(), currentPost.getPost());
    }

    @Override
    protected void like() {
        super.like();
        Suggestion.dislikedSuggestedPost(User.getCurrentUser(), currentPost.getPost());
    }

    @Override
    public void clear() {
        super.clear();
        userViews.clear();
    }

    public void addAllUsers(Collection<User> users) {
        userViews.addAll(users.stream().map(UserView::new).toList());
    }

}
