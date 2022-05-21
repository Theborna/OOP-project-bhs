package com.project.controllers;

import com.project.App;
import com.project.view.general.FeedView;

public class SecondaryController implements Controller {

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        System.out.println("kos mikham with input: " + input);
        switch (input) {
            case "feed":
                App.setView(new FeedView());
                break;
            default:
                break;
        }
    }

}
