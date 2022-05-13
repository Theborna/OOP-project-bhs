package com.project;

import com.project.util.StdOut;
import com.project.view.*;
import com.project.view.general.FeedView;
import com.project.view.general.PrimaryView;

/**
 * Main App class
 *
 */
public class App {
    private static View view;
    private static boolean running = true;

    public static void start() {
        view = new FeedView();
    }

    public static void setView(ViewsEnum nextView) {
        view = ViewsEnum.getView(nextView);
    }

    public static void setView(View view) {
        App.view = view;
    }

    public static void stop() {
        running = false;
    }

    public static void main(String... args) throws Exception {
        start();
        while (running) {
            StdOut.viewBegin(view);
            view.show();
        }
    }
}
