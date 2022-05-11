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
        rule();
        println("inside secondary");
        rule();
        print(StdColor.YELLOW + "enter next command: " + StdColor.RESET);
        controller.parse(StdIn.nextLine());
    }

    @Override
    public Controller getController() {
        return controller;
    }
}
