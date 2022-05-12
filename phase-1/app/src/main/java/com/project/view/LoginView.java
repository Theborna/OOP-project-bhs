package com.project.view;

import static com.project.util.StdOut.*;

import com.project.App;
import com.project.controllers.LoginController;
import com.project.models.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;

public class LoginView implements View {

    private LoginController controller = new LoginController();
    private String username, password;
    private User user;

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
        if ((user = controller.logToUser(username, password)) == null) {// gets the user
            // TODO : actually get the user and login
            println("no such user exists!", StdColor.RED);
            prompt("do you want to register or login?");
            String next = StdIn.nextLine();
            if (next.equals("register"))
                App.setView(ViewsEnum.REGISTER);
            else
                App.setView(ViewsEnum.LOGIN);
            return;
        }
        print("login successful! ", StdColor.GREEN);
        println("user: " + username);
        App.setView(ViewsEnum.SECONDARY);
        rule();
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginController getController() {
        return controller;
    }

}