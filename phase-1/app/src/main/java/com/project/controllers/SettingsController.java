package com.project.controllers;

import com.project.AppRegex;
import com.project.crypt;
import com.project.models.node.user.User;
import com.project.util.exception.changeViewException;

public class SettingsController implements Controller {

    private User user;
    private String username;
    private String password;
    private String name;
    private String bio;
    private boolean visible;

    public SettingsController withUser(User currentUser) {
        this.user = currentUser;
        init();
        return this;
    }

    private void init() {
        username = user.getUsername();
        password = user.getPassword();
        name = user.getName();
        // TODO: add bio
        visible = user.isPublic();
    }

    @Override
    public void parse(String input) throws changeViewException {
        input = input.trim().toLowerCase();
        // Matcher m;
        if (input.equals("help"))
            help();
        else if (input.equals("confirm"))
            confirm();
    }

    @Override
    public void help() {

    }

    public void confirm() {// TODO : complete these
        user.setName(name);
        user.setPassword(crypt.encryptedString(password + user.getSalt()));
        user.setUsername(username);
        user.setPublic(visible);
        user.sendToDB();
    }

    public boolean setUserName(String username) {
        if (!AppRegex.USERNAME.matches(username))
            return false;
        this.username = username;
        return true;
    }

    public boolean setPassword(String password) {
        if (!AppRegex.PASSWORD.matches(password))
            return false;
        this.password = password;
        return true;
    }

    public boolean setFullName(String name) {
        if (!AppRegex.FULL_NAME.matches(name))
            return false;
        this.name = name;
        return true;
    }

    public boolean setBio(String bio) {
        if (!AppRegex.BIOGRAPHY.matches(bio))
            return false;
        this.bio = bio;
        return true;
    }

    public boolean setVisibility(String visibility) {
        visibility = visibility.toLowerCase().trim();
        if (!visibility.equals("public") && !visibility.equals("private"))
            return false;
        this.visible = visibility.equals("public");
        return true;
    }

}
