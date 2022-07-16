package com.view.general;

import com.controllers.Controller;
import com.controllers.SecondaryController;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

public class SecondaryView implements View {

    private SecondaryController controller = new SecondaryController();
    private static SecondaryView instance;

    private SecondaryView() {

    }

    public static SecondaryView getInstance() {
        if (instance == null)
            instance = new SecondaryView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
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

}
