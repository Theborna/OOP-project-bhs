package com.project.controllers;

import com.project.App;
import com.project.view.ViewsEnum;

public class PrimaryController implements Controller {

    @Override
    public void parse(String input) {
        switch (input.toLowerCase()) {
            case "login":
                App.setView(ViewsEnum.LOGIN);
                break;
            case "secondary":
                App.setView(ViewsEnum.SECONDARY);
                break;
            default:
                break;
        }
    }

}
