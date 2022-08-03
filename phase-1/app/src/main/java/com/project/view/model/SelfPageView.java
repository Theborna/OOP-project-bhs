package com.project.view.model;

import com.database.UserDB;
import com.project.controllers.SelfPageController;
import com.project.models.connection.PostUserConnection;
import com.project.models.node.user.BusinessUser;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import java.sql.SQLException;
import java.util.Collection;

import static com.project.util.StdOut.*;

public class SelfPageView extends PageView {
    private static SelfPageView instance;

    protected SelfPageView() {
        controller = new SelfPageController();
        user = User.getCurrentUser();
        controller.addAll(PostUserConnection.getPosts(User.getCurrentUser().getId()));
        controller.getCurrent();
    }

    public static SelfPageView getInstance() {
        // if (instance == null)
        instance = new SelfPageView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        if (((SelfPageController) controller).isNeedInfo())
            gaveBasic = false;
        if (!gaveBasic) {
            info();
            int ans = 0;
            String input;
            do {
                println("");
                printSelections("Y", "N","show -followers","show -following");
                prompt("continue?");
                input = StdIn.nextLine();
                if(input.equals("show -followers"))
                    showFollowers();
                else if (input.equals("show -following"))
                    showFollowing();
            } while ((ans = ((SelfPageController) controller).isContinue(input)) == 3);
            if (ans == 2)
                return;
            gaveBasic = true;
        }
        super.showSuper();
    }

    private void show(Collection<User> users){
        for(User u : users)
            println(u.getUsername(), StdColor.MAGENTA_UNDERLINED);
        if(users.size() == 0)
            println("no data to show", StdColor.CYAN);
    }

    private void showFollowing() {
        try {
            show(UserDB.getFollowings(User.getCurrentUser().getId(),0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showFollowers() {
        try {
            show(UserDB.getFollowers(User.getCurrentUser().getId(),0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void printCommands() {
        if (User.getCurrentUser() instanceof BusinessUser)
            printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "info", "settings",
                    "comment", "show -comments", "show -stats","show -followers","delete");
        else
            printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "info", "settings",
                    "comment", "show -comments", "show -following","delete");
    }

    @Override
    public PageView setUser(User user) {
        return instance;
    }

    public View refresh() {
        user = User.getCurrentUser();
        return this;
    }

}
