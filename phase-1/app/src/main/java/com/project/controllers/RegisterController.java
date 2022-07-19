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

}
