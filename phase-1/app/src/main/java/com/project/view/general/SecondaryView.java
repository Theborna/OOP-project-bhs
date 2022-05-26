package com.project.view.general;

import static com.project.util.StdOut.*;

import com.project.controllers.Controller;
import com.project.controllers.SecondaryController;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.view.View;

public class SecondaryView implements View {

    private User user;
    private SecondaryController controller = new SecondaryController();
    private static SecondaryView instance;

    private SecondaryView() {

    }

    public static SecondaryView getInstance() {
        if (instance == null)
            instance = new SecondaryView();
        return instance;
    }

    public SecondaryView setUser(User user) {
        if (user == null)
            return null;
        this.user = user;
        controller.setUser(user);
        return this;
    }

    @Override
    public void show() {
        println("inside secondary");
        printSelections("Chat", "Feed", "Search", "new Post", "Page");
        prompt("enter next view");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }

    public User getUser() {
        return user;
    }
}
