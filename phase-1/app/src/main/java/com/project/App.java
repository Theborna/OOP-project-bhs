package com.project;

import com.project.util.StdOut;
import com.project.view.*;
import com.project.view.general.PrimaryView;

/**
 * Main App class
 *
 */
public class App {
    private static View view;
    private static LimitedList<View> pastViews = new LimitedList<>(10);
    private static boolean running = true;

    public static void start() {
        setView(new PrimaryView());
    }

    public static void setView(View view) {
        App.view = view;
        pastViews.add(view);
    }

    public static void stop() {
        running = false;
    }

    public static void main(String... args) throws Exception {
        start();
        while (running) {
            StdOut.viewBegin(view);
            view.show();
            System.out.println(pastViews);
        }
    }
}
