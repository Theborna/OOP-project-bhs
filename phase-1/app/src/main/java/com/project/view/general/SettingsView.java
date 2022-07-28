package com.project.view.general;

import com.project.controllers.Controller;
import com.project.controllers.SettingsController;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.util.regex.Matcher;

import com.project.App;
import com.project.AppRegex;

public class SettingsView implements View {

    private SettingsController controller;

    public SettingsView() {
        controller = new SettingsController().withUser(User.getCurrentUser());
    }

    @Override
    public void show() throws changeViewException {
        prompt("enter next command");
        String input;
        Matcher m;
        controller.parse(input = StdIn.nextLine());
        if ((m = AppRegex.SET_USERNAME.getMatcher(input)) != null) {
            if (controller.setUserName(m.group("username")))
                println("username changed to " + m.group("username"), StdColor.GREEN);
            else
                printError("invalid username format");
            return;
        } else if ((m = AppRegex.SET_PASSWORD.getMatcher(input)) != null) {
            if (controller.setPassword(m.group("password")))
                println("password changed to " + m.group("password"), StdColor.GREEN);
            else
                printError("invalid password format");
            return;
        } else if ((m = AppRegex.SET_NAME.getMatcher(input)) != null) {
            if (controller.setFullName(m.group("name")))
                println("name changed to " + m.group("name"), StdColor.GREEN);
            else
                printError("invalid name format");
            return;
        } else if ((m = AppRegex.SET_BIO.getMatcher(input)) != null) {
            if (controller.setBio(m.group("bio")))
                println("bio changed to " + m.group("bio"), StdColor.GREEN);
            else
                printError("invalid bio format, maximum length 250 characters");
            return;
        } else if ((m = AppRegex.SET_VISIBILITY.getMatcher(input)) != null) {
            if (controller.setVisibility(m.group("visibility")))
                println("visibility changed to " + m.group("visibility"), StdColor.GREEN);
            else
                printError("invalid visibility format, choose from (private, public)");
            return;
        } else if (input.trim().toLowerCase().equals("-confirm")) {
            controller.confirm();
            App.setView(SecondaryView.getInstance());
        } else if (input.trim().toLowerCase().equals("-cancel")) {
            App.setView(SecondaryView.getInstance());
        } else if (input.trim().toLowerCase().equals("-help")) {
            controller.help();
        } else
            printError("invalid input, try (-help)");
    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub

    }

}
