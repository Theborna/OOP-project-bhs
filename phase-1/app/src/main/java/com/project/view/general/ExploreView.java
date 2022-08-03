package com.project.view.general;

import com.project.controllers.ExploreController;
import com.project.models.connection.PostUserConnection;
import com.project.models.connection.UserFollowingConnection;
import com.project.models.node.user.User;
import com.project.util.exception.changeViewException;

public class ExploreView extends FeedView {

    private static ExploreView instance;

    private ExploreView() {
        controller = new ExploreController();
        getFeed();
        controller.getCurrent();
    }

    public void getSuggestedUsers() {
        ((ExploreController)controller).clear();
        ((ExploreController)controller).addAllUsers(UserFollowingConnection.getExploreUsers(User.getCurrentUser()));
    }
    
    @Override
    public void show() throws changeViewException {
        super.show();
    }



    public static ExploreView getInstance() {
        // if (instance == null)
        instance = new ExploreView();
        return instance;
    }

    @Override
    public void getFeed() {
        controller.clear();
        controller.addAll(PostUserConnection.getExplore(User.getCurrentUser()));
    }

}
