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
        StdOut.rule();
        println("inside primary");
        StdOut.rule();
        print("enter next view: ", StdColor.YELLOW);
        controller.parse(StdIn.nextLine());
    }

    @Override
    public Controller getController() {
        return controller;
    }
}
