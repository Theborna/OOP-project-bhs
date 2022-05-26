package com.project.controllers;

import com.project.App;
import com.project.view.general.HelpView;
import com.project.view.general.LoginView;
import com.project.view.general.RegisterView;
import com.project.view.general.SecondaryView;

public class PrimaryController implements Controller {

    @Override
    public void parse(String input) {
        switch (input.toLowerCase()) {
            case "login":
                App.setView(LoginView.getInstance());
                break;
            case "secondary":
                App.setView(SecondaryView.getInstance());
                break;
            case "help":
                App.setView(HelpView.getInstance());
            case "register":
                App.setView(RegisterView.getInstance());
            default:
                break;
        }
    }

}
