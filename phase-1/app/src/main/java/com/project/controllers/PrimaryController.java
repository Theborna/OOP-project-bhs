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
                App.setView(new LoginView());
                break;
            case "secondary":
                App.setView(new SecondaryView());
                break;
            case "help":
                App.setView(new HelpView());
            case "register":
                App.setView(new RegisterView());
            default:
                break;
        }
    }

}
