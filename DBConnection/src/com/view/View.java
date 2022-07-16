package com.view;

import com.controllers.Controller;
import com.util.StdOut;
import com.util.exception.changeViewException;
import com.view.general.*;

/**
 * parent class for all view classes
 * 
 * @methods show(), getController()
 */
public interface View {
    public void show() throws changeViewException, changeViewException;

    public <T extends Controller> T getController();

    public static View getView(String name) {
        switch (name) {
            case "primary":
                return PrimaryView.getInstance();
            case "secondary":
                return SecondaryView.getInstance();
            case "feed":
                return FeedView.getInstance();
            case "login":
                return LoginView.getInstance();
            case "register":
                return RegisterView.getInstance();
            default:
                StdOut.printError("no such view exists");
                break;
        }
        return null;
    }
}
