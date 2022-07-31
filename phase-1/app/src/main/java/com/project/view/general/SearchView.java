package com.project.view.general;

import com.project.App;
import com.project.AppRegex;
import com.project.controllers.Controller;
import com.project.controllers.SearchController;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import java.util.regex.Matcher;

import static com.project.util.StdOut.*;

public class SearchView implements View {

    private static SearchView instance;
    private SearchController controller;

    private SearchView(){
        controller = new SearchController();
    }

    @Override
    public void show() throws changeViewException {
        printSelections("-search -user <username>","-search -chat <chat-name>", "-search -post <post-text>");
        String input = StdIn.nextLine();
        Matcher m;
        if((m = AppRegex.SEARCH_USER.getMatcher(input)) != null)
            showUserResult(m.group("username"));
        else if ((m = AppRegex.SEARCH_CHAT.getMatcher(input)) != null)
            showChatResult(m.group("chat"));
        else if ((m = AppRegex.SEARCH_POST.getMatcher(input)) != null)
            showPostResult(m.group("post"));
    }

    private void showUserResult(String username){
        switch (controller.userResult(username)){
            case 0:
                break;
            case 1:
                printError("invalid username format");
                break;
            default:
                break;
        }
    }

    private void showChatResult(String chat){
        switch (controller.chatResult(chat)){
            case 0:
                break;
            case 1:
                printError("invalid username format");
                break;
            default:
                break;
        }
    }

    private void showPostResult(String post){
        switch (controller.postResult(post)){
            case 0:
                break;
            case 1:
                printError("invalid username format");
                break;
            default:
                break;
        }
    }

    public static SearchView getInstance() {
        instance = new SearchView();
        return instance;
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    @Override
    public void reset() {
        instance = null;
    }
}
