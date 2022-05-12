package com.project.view;

import com.project.controllers.RegisterController;
import com.project.models.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;

import static com.project.util.StdOut.*;

import com.project.App;

public class RegisterView implements View {

    private String username, password;
    private User user;
    private RegisterController controller = new RegisterController();

    @Override
    public void show() {
        while (username == null) {
            prompt("enter username");
            username = controller.getUsername(StdIn.nextLine());
            if (username == null)
                println(StdColor.RED + "invalid username format");
        }
        while (password == null) {
            prompt("enter password");
            password = controller.getPassword(StdIn.nextLine());
            if (password == null)
                println(StdColor.RED + "invalid password format");
        }
        if ((user = controller.logToUser(username, password)) == null) {
            print("register successful! ", StdColor.GREEN);
            // TODO: add user to DB
        } else {
            println("user already exists", StdColor.MAGENTA);
            prompt("do you want to register or login?");
            String next = StdIn.nextLine();
            if (next.equals("register"))
                App.setView(ViewsEnum.REGISTER);
            else
                App.setView(ViewsEnum.LOGIN);
            return;
        }
        println("user: " + username + ", password: " + password);
        App.setView(ViewsEnum.SECONDARY);
        rule();
    }

    @Override
    @SuppressWarnings("unchecked")
    public RegisterController getController() {
        return controller;
    }

}