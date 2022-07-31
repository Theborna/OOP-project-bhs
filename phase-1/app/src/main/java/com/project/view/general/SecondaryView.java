package com.project.view.general;

import static com.project.util.StdOut.*;

import com.project.controllers.Controller;
import com.project.controllers.SecondaryController;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

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
        printSelections("Chat", "Feed", "Explore", "Page", "Settings","Search");
        prompt("enter next view");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }

    @Override
    public void reset() {
        instance = null;
    }
}
