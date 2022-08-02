package com.project.view.general;

import com.project.controllers.ExploreController;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.user.User;

public class ExploreView extends FeedView {

    private static ExploreView instance;

    private ExploreView() {
        controller = new ExploreController();
        getFeed();
        controller.getCurrent();
    }

    public static ExploreView getInstance() {
        if (instance == null)
            instance = new ExploreView();
        return instance;
    }

    @Override
    public void getFeed() {
        controller.clear();
        controller.addAll(PostUserConnection.getExplore(User.getCurrentUser()));
    }

}
