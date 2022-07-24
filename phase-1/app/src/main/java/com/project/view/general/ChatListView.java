package com.project.view.general;

import com.project.controllers.ChatListController;
import com.project.controllers.Controller;
import com.project.controllers.ListController;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import com.project.view.model.ChatItemView;

import static com.project.util.StdOut.*;

public class ChatListView implements View {

    private static ChatListView instance;
    protected ListController<ChatItemView> controller;

    protected ChatListView() {
        controller = new ChatListController();
        ((ChatListController) controller).addAll(User.getCurrentUser().getChats());
        controller.getCurrent();
    }

    public static ChatListView getInstance() {
        if (instance == null)
            instance = new ChatListView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        controller.getCurrent().show();
        showSelections();
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    protected void showSelections() {
        printSelections("next", "last", "show -all", "show <chat id>", "top", "open", "new chat");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Controller getController() {
        return controller;
    }

    @Override
    public void reset() {
        instance = null;        
    }

}