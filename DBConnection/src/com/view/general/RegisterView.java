package com.view.general;


import com.App;
import com.controllers.RegisterController;
import com.models.node.user.User;
import com.util.StdColor;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

public class RegisterView implements View {

    private RegisterController controller = new RegisterController();
    private static RegisterView instance;

    private RegisterView() {

    }

    public static RegisterView getInstance() {
        if (instance == null)
            instance = new RegisterView();
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
        if ((user = controller.logToUser(username, password)) == null) {
            print("register successful! ", StdColor.GREEN);
            // TODO: add user to DB
        } else {
            println("user already exists", StdColor.MAGENTA);
            printSelections("register", "login");
            prompt("do you want to register or login?");
            String next = StdIn.nextLine();
            if (next.equalsIgnoreCase("register"))
                App.setView(RegisterView.getInstance());
            else if (next.equalsIgnoreCase("login"))
                App.setView(LoginView.getInstance());
            return;
        }
        println("user: " + username + ", password: " + password);
        App.setView(SecondaryView.getInstance());
        // rule();
    }

    @Override
    @SuppressWarnings("unchecked")
    public RegisterController getController() {
        return controller;
    }

}