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
    private static boolean rule;

    public static void start() {
        for (int i = 0; i++ < 20;)
            System.out.println();
        Log.init();
        setView(PrimaryView.getInstance());
        rule = true;
    }

    public static void setView(View view) {
        setView(view, true);
    }

    public static void setView(View view, boolean addTo) {
        if (view == null)
            return;
        App.view = view;
        Log.logger.info("changed view to " + view.getClass().getSimpleName());
        rule = true;
        if (addTo)
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
            if (rule) {
                StdOut.viewBegin(view);
                rule = false;
            }
            try {
                view.show();
            } catch (changeViewException e) {
                setView(e.getView(), false);
            }
        }
    }

}
