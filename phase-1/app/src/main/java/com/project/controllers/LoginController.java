package com.project.controllers;

import com.database.UserDB;
import com.project.AppRegex;
import com.project.models.node.user.User;

import java.sql.SQLException;

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
    public String getRepeatPassword(String nextLine, String password) {
        if (nextLine.equals(password))
            return password;
        return null;
    }

    public User logToUser(String username, String password) {
            return User.logToUser(username, password);
    }

    public User logToUser(String username) {
        return User.logToUser(username);
    }

    public boolean exists(String usName) {
        try {
            return UserDB.getUserInfo(usName) != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void help() {
        // no help needed
    }

}
