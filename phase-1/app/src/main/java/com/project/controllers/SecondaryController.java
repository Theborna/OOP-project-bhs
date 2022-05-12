package com.project.controllers;

import com.project.util.StdIn;

public class SecondaryController implements Controller {

    String command;

    @Override
    public void parse(String input) {
        System.out.println("kos mikham with input: " + input);
    }

}
