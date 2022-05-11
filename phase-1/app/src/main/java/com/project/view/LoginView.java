package com.project.view;

import static com.project.util.StdOut.*;

import com.project.App;
import com.project.controllers.Controller;
import com.project.controllers.LoginController;
import com.project.util.StdColor;
import com.project.util.StdIn;

public class LoginView implements View {

    private LoginController controller = new LoginController();
    private String username, password;

    @Override
    public void show() {
        rule();
        while (username == null) {
            print("enter username: ", StdColor.YELLOW);
            username = controller.getUsername(StdIn.nextLine());
            if (username == null)
                println(StdColor.RED + "invalid username format");
        }
        while (password == null) {
            print("enter password: ", StdColor.YELLOW);
            password = controller.getPassword(StdIn.nextLine());
            if (password == null)
                println(StdColor.RED + "invalid password format");
        }
        if (!validUser(username, password)) {
            println("no such user exists!", StdColor.RED);
            App.setView(ViewsEnum.LOGIN);
            return;
        }
        print("login successfull! ", StdColor.GREEN);
        println("user: " + username);
        App.setView(ViewsEnum.SECONDARY);
        rule();
    }

    private boolean validUser(String username2, String password2) {
        return true;
    }

    @Override
    public Controller getController() {
        return controller;
    }

}
