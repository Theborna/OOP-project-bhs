package com.view.general;

import com.App;
import com.controllers.LoginController;
import com.models.node.user.User;
import com.util.StdColor;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

public class LoginView implements View {

    private LoginController controller = new LoginController();
    private static LoginView instance;

    private LoginView() {

    }

    public static LoginView getInstance() {
        if (instance == null)
            instance = new LoginView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        String username = null, password = null;
        User user = null;
        while (username == null) {
            prompt("enter username");
            username = controller.getUsername(StdIn.nextLine());
            if (username == null)
                printError("invalid username format");
        }
        while (password == null) {
            prompt("enter password");
            password = controller.getPassword(StdIn.nextLine());
            if (password == null)
                printError("invalid password format");
        }
        if ((user = controller.logToUser(username, password)) == null) {// gets the user
            printError("no such user exists!");
            prompt("do you want to register or login?");
            String next = StdIn.nextLine();
            if (next.equals("register"))
                App.setView(RegisterView.getInstance());
            else
                App.setView(LoginView.getInstance());
            return;
        }
        print("login successful! ", StdColor.GREEN);
        println("user: " + username);
        App.setView(SecondaryView.getInstance());
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginController getController() {
        return controller;
    }

}
