package com.project.view.general;

import static com.project.util.StdOut.*;

import com.project.controllers.Controller;
import com.project.controllers.SecondaryController;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.view.View;

public class SecondaryView implements View {

    private Controller controller = new SecondaryController();

    @Override
    public void show() {
        println("inside secondary");
        // rule();
        printSelections("Chat", "Feed", "Search");
        prompt("enter next view");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }
}
