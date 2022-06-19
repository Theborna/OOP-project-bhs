package com.project.controllers;

import com.project.App;
import com.project.util.StdColor;
import com.project.view.general.LoginView;
import com.project.view.general.RegisterView;
import static com.project.util.StdOut.*;

public class PrimaryController implements Controller {

    @Override
    public void parse(String input) {
        switch (input.toLowerCase()) {
            case "login":
                App.setView(LoginView.getInstance());
                break;
            case "help":
                help();
                break;
            case "register":
                App.setView(RegisterView.getInstance());
            default:
                break;
        }
    }

    public void help() {
        rule('*');
        print("help:", StdColor.MAGENTA_UNDERLINED);
        println(" brings up the help menu");
        print("login:", StdColor.MAGENTA_UNDERLINED);
        println(" brings up the login menu, you will need a username and password to login");
        print("register:", StdColor.MAGENTA_UNDERLINED);
        println(" brings up the register menu, you will create a new account in this page");
        rule('*');
    }

}
