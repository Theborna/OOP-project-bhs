package com.project.view.general;

import com.project.controllers.RegisterController;
import com.project.models.node.user.BusinessUser;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.util.Date;

import com.project.App;

public class RegisterView implements View {

    private RegisterController controller = new RegisterController();
    private static RegisterView instance;

    private RegisterView() {

    }

    public static RegisterView getInstance() {
        if (instance == null)
            instance = new RegisterView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        String username = null, password = null, repeatPassword = null, fullName = null, email = null, type = null,
                visible = null;
        Date birthDate = null;
        String input;
        User user = null;
        while (username == null) {
            prompt("enter username");
            username = controller.getUsername(input = StdIn.nextLine());
            if (username == null)
                printError("invalid username format");
        }
        while (password == null) {
            prompt("enter password");
            password = controller.getPassword(input = StdIn.nextLine());
            if (password == null)
                printError("invalid password format");
        }
        while (repeatPassword == null) {
            prompt("repeat password");
            repeatPassword = controller.getRepeatPassword(input = StdIn.nextLine(), password);
            if (repeatPassword == null)
                printError("not the same");
        }
        while (fullName == null) {
            prompt("enter full name");
            fullName = controller.getFullName(input = StdIn.nextLine());
            if (fullName == null)
                printError("invalid full name format");
        }
        while (email == null) {
            prompt("enter your email address");
            email = controller.getEmail(input = StdIn.nextLine());
            if (email == null)
                printError("invalid email format");
        }
        while (birthDate == null) {
            prompt("enter birth date(yyyy-mm-dd) or \"--skip\" to skip this part");
            if ((input = StdIn.nextLine()).equals("--skip")) {
                println("skipped", StdColor.GREEN);
                break;
            }
            birthDate = controller.getBirthDate(input);
            if (birthDate == null)
                printError("invalid birth date format");
        }
        if ((user = controller.logToUser(username, password)) == null) {
            println("register successful! ", StdColor.GREEN);
            while (type == null) {
                printSelections("normal", "business");
                prompt("what type of user do you want to be?");
                type = controller.getType(input = StdIn.nextLine());
                if (type == null)
                    printError("invalid type of user");
            }
            while (visible == null) {
                printSelections("private", "public");
                prompt("select account visibility");
                visible = controller.getVisibility(input = StdIn.nextLine());
                if (visible == null)
                    printError("invalid input");
            }
            print("register completed! ", StdColor.GREEN);
            if (type.equals("normal"))
                user = new NormalUser(username, password);
            else
                user = new BusinessUser(username, password);
            user.setPublic(visible.equals("public")).setBirthDate(birthDate).sendToDB();
        } else {
            println("user already exists", StdColor.MAGENTA);
            printSelections("register", "login");
            prompt("do you want to register or login?");
            String next = StdIn.nextLine();
            if (next.equalsIgnoreCase("register"))
                App.setView(RegisterView.getInstance());
            else if (next.equalsIgnoreCase("login"))
                App.setView(LoginView.getInstance());
            return;
        }
        println("user: " + username + ", password: " + password + ", visibility: " + visible
                + ", email: " + email + ", type: " + type + ", born at: "
                + birthDate.toString().replaceAll("\\d{2}:\\d{2}:\\d{2} ", ""));
        App.setView(SecondaryView.getInstance());
        // rule();
    }

    @Override
    @SuppressWarnings("unchecked")
    public RegisterController getController() {
        return controller;
    }

    @Override
    public void reset() {
        instance = null;
    }
}
