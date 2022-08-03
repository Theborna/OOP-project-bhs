package com.project.view.general;

import static com.project.util.StdOut.*;

import com.project.App;
import com.project.controllers.LoginController;
import com.project.crypt;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

public class LoginView implements View {

    private LoginController controller = new LoginController();
    private static LoginView instance;

    private LoginView() {

    }

    public static LoginView getInstance() {
        if (instance == null)
            instance = new LoginView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        String username = null, password = null;
        User user = null;
        while (username == null) {
            prompt("enter username");
            username = controller.getUsername(StdIn.nextLine());
            if (username == null)
                printError("invalid username format");
        }
        while (password == null) {
            prompt("enter password( use -forgot if you forgot your password )");
            String input = StdIn.nextLine();
            password = controller.getPassword(input);
            if (input.equals("-forgot")) {
                password = forgot(username);
            } else if (password == null)
                printError("invalid password format");
        }
        if ((user = controller.logToUser(username, password)) == null) {// gets the user
            printError("no such user exists!");
            prompt("do you want to register or login?");
            String next = StdIn.nextLine();
            if (next.equals("register"))
                App.setView(RegisterView.getInstance());
            else
                App.setView(LoginView.getInstance());
            return;
        }
        print("login successful! ", StdColor.GREEN);
        println("user: " + username);
        App.setView(SecondaryView.getInstance());
    }

    private String forgot(String username) throws changeViewException {
        String question, answer, userAns = null;
        User us =  User.logToUser(username);
        question= us.getSecType().toString();
        answer = us.getSecAns();
        while(userAns == null){
            prompt(question);
            if(!(userAns =crypt.encryptedString(StdIn.nextLine()+us.getSalt())).equals(answer)){
                printError("incorrect answer");
                userAns = null;
            }
        }
        String password= null, repeatPassword= null;
        while (password == null) {
            prompt("enter password");
            password = controller.getPassword(StdIn.nextLine());
            if (password == null)
                printError("invalid password format");
        }
        while (repeatPassword == null) {
            prompt("repeat password");
            repeatPassword = controller.getRepeatPassword(StdIn.nextLine(), password);
            if (repeatPassword == null)
                printError("not the same");
        }
        print("password changed successfully! ",StdColor.GREEN);
        println("new password: "+ password);
        password = crypt.encryptedString(password + us.getSalt());
        us.setPassword(password).sendToDB();
        return password;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginController getController() {
        return controller;
    }

    @Override
    public void reset() {
        instance = null;
    }

}
