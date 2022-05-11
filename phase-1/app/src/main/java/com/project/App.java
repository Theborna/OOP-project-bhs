package com.project;

import com.project.util.StdIn;
import com.project.view.*;

/**
 * Main App class
 *
 */
public class App {
    private static View view;
    private static boolean running = true;

    public static void start() {
        view = new PrimaryView();
    }

    public static void setView(ViewsEnum nextView) {
        view = ViewsEnum.getView(nextView);
    }

    public static void stop() {
        running = false;
    }

    public static void main(String... args) throws Exception {
        start();
        while (running)
            view.show();
    }
}
