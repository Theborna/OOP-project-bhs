package com.project;

import com.project.util.Log;
import com.project.util.StdOut;
import com.project.util.exception.changeViewException;
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
        setView(PrimaryView.getInstance());
        Log.init();
    }

    public static void setView(View view) {
        App.view = view;
        Log.logger.info("changed view to " + view.getClass().getSimpleName());
        pastViews.add(view);
    }

    public static void stop() {
        running = false;
    }

    public static View lastView() {
        return (pastViews.next());
    }

    public static void main(String... args) throws Exception {
        start();
        while (running) {
            StdOut.viewBegin(view);
            try {
                view.show();
            } catch (changeViewException e) {
                setView(e.getView());
            }
        }
    }

}
