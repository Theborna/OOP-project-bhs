package com.project.view;

import static com.project.util.StdOut.*;

import com.project.controllers.Controller;
import com.project.controllers.PrimaryController;
import com.project.util.StdIn;
import com.project.util.StdOut;
import com.project.util.StdColor;

public class PrimaryView implements View {

    private Controller controller = new PrimaryController();

    @Override
    public void show() {
        println("inside primary");
        StdOut.rule();
        prompt("enter the next view");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }
}
