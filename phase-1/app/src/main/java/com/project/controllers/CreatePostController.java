package com.project.controllers;

import com.project.util.StdIn;
import com.project.util.exception.changeViewException;

public class CreatePostController implements Controller {

    @Override
    public void parse(String input) {// unnecessary
    }

    public boolean getPostText(StringBuilder postText) throws changeViewException {
        if (postText == null)
            postText = new StringBuilder();
        String input;
        while (!(input = StdIn.nextLine()).equals("")) {
            if (input.equals("-done"))
                break;
            postText.append(input + "\n");
        }
        return true;
    }

    @Override
    public void help() {
        // no help needed
    }

}
