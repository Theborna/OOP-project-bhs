package com.project.view;

import com.project.controllers.Controller;
import com.project.util.StdOut;
import com.project.util.exception.changeViewException;
import com.project.view.general.ChatListView;
import com.project.view.general.ChatView;
import com.project.view.general.CreatePostView;
import com.project.view.general.ExploreView;
import com.project.view.general.FeedView;
import com.project.view.general.ForwardView;
import com.project.view.general.LoginView;
import com.project.view.general.NewChatView;
import com.project.view.general.PrimaryView;
import com.project.view.general.RegisterView;
import com.project.view.general.SearchView;
import com.project.view.general.SecondaryView;

/**
 * parent class for all view classes
 * 
 * @methods show(), getController()
 */
public interface View {
    public void show() throws changeViewException;

    public <T extends Controller> T getController();

    public static View getView(String name) {
        switch (name) {
            case "primary":
                return PrimaryView.getInstance();
            case "secondary":
                return SecondaryView.getInstance();
            case "feed":
                return FeedView.getInstance();
            case "login":
                return LoginView.getInstance();
            case "register":
                return RegisterView.getInstance();
            default:
                StdOut.printError("no such view exists");
                break;
        }
        return null;
    }

    public static void resetAll() {
        ChatListView.getInstance().reset();
        ChatView.getInstance().reset();
        // CreatePostView.getInstance().reset();
        ExploreView.getInstance().reset();
        FeedView.getInstance().reset();
        ForwardView.getInstance().reset();
        NewChatView.getInstance().reset();
        // SearchView.getInstance().reset();

    }

    public void reset();
}
