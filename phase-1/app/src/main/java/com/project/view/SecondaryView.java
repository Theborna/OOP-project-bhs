package com.project.view;

import static com.project.util.StdOut.*;

import com.project.controllers.Controller;
import com.project.controllers.SecondaryController;
import com.project.util.StdColor;
import com.project.util.StdIn;

public class SecondaryView implements View {

    private Controller controller = new SecondaryController();

    @Override
    public void show() {
        println("inside secondary");
        rule();
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }
}
