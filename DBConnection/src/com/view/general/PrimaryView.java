package com.view.general;

import com.controllers.Controller;
import com.controllers.PrimaryController;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

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
    public void show() throws changeViewException {
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
