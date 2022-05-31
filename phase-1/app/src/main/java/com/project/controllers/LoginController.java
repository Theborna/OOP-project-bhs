package com.project.controllers;

import com.project.AppRegex;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

public class LoginController implements Controller {

    @Override
    public void parse(String input) {
    }

    public String getUsername(String input) {
        if (AppRegex.USERNAME.getMatcher(input) != null)
            return input;
        return null;
    }

    public String getPassword(String nextLine) {
        if (AppRegex.PASSWORD.getMatcher(nextLine) != null)
            return nextLine;
        return null;
    }

    public User logToUser(String username, String password) {
        return User.logToUser(username, password);
    }

}
