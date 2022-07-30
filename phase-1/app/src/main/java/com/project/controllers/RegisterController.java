package com.project.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.project.AppRegex;

public class RegisterController extends LoginController {

    @Override
    public void parse(String input) {
    }

    public String getRepeatPassword(String nextLine, String password) {
        if (nextLine.equals(password))
            return password;
        return null;
    }

    public String getFullName(String nextLine) {
        if (AppRegex.FULL_NAME.matches(nextLine))
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

}
