package com.project.view.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.project.controllers.Controller;
import com.project.models.connection.PostConnection;
import com.project.models.node.Post;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import com.project.view.model.PostView;

import static com.project.util.StdOut.*;

public class FeedView implements View {
    private static FeedView instance;

    private Set<PostView> postViews = new HashSet<PostView>();

    private FeedView() {

    }

    public Set<PostView> getChildren() {
        return postViews;
    }

    public void clear() {
        postViews.clear();
    }

    public static FeedView getInstance() {
        if (instance == null)
            instance = new FeedView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        for (PostView postView : postViews)
            postView.show();
        printSelections("scroll up", "scroll down", "show post -id");
        prompt("enter next command");
        StdIn.nextLine();
    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

}
