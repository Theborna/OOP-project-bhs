package com.project.view.general;

import static com.project.util.StdOut.*;

import com.project.controllers.Controller;
import com.project.controllers.PrimaryController;
import com.project.util.StdIn;
import com.project.util.StdOut;
import com.project.view.View;
import com.project.util.StdColor;

public class PrimaryView implements View {

    private Controller controller = new PrimaryController();

    private static PrimaryView instance;

    private PrimaryView() {

    }

    public static PrimaryView getInstance() {
        if (instance == null)
            instance = new PrimaryView();
        return instance;
    }

    @Override
    public void show() {
        println("inside primary");
        // StdOut.rule();
        printSelections("Login", "Register", "Help");
        prompt("enter the next view");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }
}
