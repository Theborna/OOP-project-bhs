package com.project;

import com.project.util.StdIn;
import com.project.view.*;

/**
 * Main App class
 *
 */
public class App {
    private static View view;

    public static void start() {
        view = new PrimaryView();
    }

    public static void setView(String nextView) {
        switch (nextView) {
            case "primary":
                view = new PrimaryView();
                break;
            default:
                System.out.println("no such view: " + nextView);
                break;
        }
    }

    public static void main(String... args) throws Exception {
        start();
        String input;
        while ((input = StdIn.nextLine()) != null) {
            InputProcessor.parse(input);
            view.show();
        }
    }
}
