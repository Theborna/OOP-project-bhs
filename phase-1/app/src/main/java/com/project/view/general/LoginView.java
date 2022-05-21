package com.project.view.general;

import static com.project.util.StdOut.*;

import com.project.App;
import com.project.controllers.LoginController;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.view.View;

public class LoginView implements View {

    private LoginController controller = new LoginController();
    private String username, password;
    private User user;

    @Override
    public void show() {
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
                App.setView(new RegisterView());
            else
                App.setView(new LoginView());
            return;
        }
        print("login successful! ", StdColor.GREEN);
        println("user: " + username);
        App.setView(new SecondaryView().setUser(user));
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginController getController() {
        return controller;
    }

}
