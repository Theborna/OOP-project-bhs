package com.electro.phase1.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.electro.phase1.AppRegex;
import com.electro.phase1.models.node.user.BusinessUser;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;
import com.electro.views.component.ErrorNotification;

public class RegisterController extends LoginController {

    // @Override
    // public void parse(String input) {
    // }
    private static RegisterController instance;

    private String email, fullName;

    public RegisterController() {
        super();
        email = null;
        fullName = null;
    }

    public String getRepeatPassword(String nextLine, String password) {
        if (nextLine.equals(password))
            return password;
        return null;
    }

    public String getFullName(String nextLine) {
        if (nextLine.matches("\\S+") && nextLine.length() <= 30)
            return nextLine;
        return null;
    }

    public java.util.Date getBirthDate(String input) {
        if (!AppRegex.DATE.matches(input))
            return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(input);
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean setEmail(String email) {
        if (getEmail(email) == null) {
            if (email.length() != 0)
                new ErrorNotification("invalid email format: " + email);
            return false;
        }
        this.email = email;
        return true;
    }

    public String getType(String string) {
        if (string.equals("normal") || string.equals("business"))
            return string;
        return null;
    }

    public String getVisibility(String string) {
        if (string.equals("public") || string.equals("private"))
            return string;
        return null;
    }

    public String getEmail(String string) {
        if (AppRegex.EMAIL.matches(string))
            return string;
        return null;
    }

    public void makeAccount(boolean isPublic, boolean isNormal, Date birthDate) {
        User user;
        if (isNormal)
            user = new NormalUser(username, password);
        else
            user = new BusinessUser(username, password);
        user.setPublic(isPublic).setBirthDate(birthDate).sendToDB();
    }
}
