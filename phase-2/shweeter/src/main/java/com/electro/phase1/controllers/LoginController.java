package com.electro.phase1.controllers;

import com.electro.phase1.AppRegex;
import com.electro.phase1.models.node.user.User;
import com.electro.views.component.ErrorNotification;

public class LoginController implements Controller {

    private static LoginController instance;

    protected String username;
    protected String password;
    protected User user;
    protected String securityQ, securityAns;

    // @Override
    // public void parse(String input) {
    // }

    public String securityQuestion(String username) {
        if (User.getID(username) == null)
            return null;
        String[] ans = User.getSecurityQuestion(username);
        securityAns = ans[1];
        password = ans[2];
        return securityQ = ans[0];
    }

    public String isCorrectSecure(String answer) {
        if (securityAns.equals(answer.toLowerCase().trim()))
            return password;
        return null;
    }

    public LoginController() {
        username = null;
        password = null;
        user = null;
    }

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    public String getUsername(String input) {
        if (AppRegex.USERNAME.getMatcher(input) != null)
            return input;
        return null;
    }

    public boolean setUsername(String username) {
        if (getUsername(username) == null) {
            if (username.length() != 0)
                new ErrorNotification("invalid username format");
            return false;
        }
        this.username = username;
        return true;
    }

    public String getPassword(String nextLine) {
        if (AppRegex.PASSWORD.getMatcher(nextLine) != null)
            return nextLine;
        return null;
    }

    public boolean setPassword(String password) {
        if (getPassword(password) == null) {
            if (password.length() != 0)
                new ErrorNotification("invalid password format");
            return false;
        }
        this.password = password;
        return true;
    }

    public User logToUser() {
        user = User.logToUser(username, password);
        return user;
    }

    @Override
    public void help() {
        // no help needed
    }

    public void reset() {
        username = null;
    }
}
