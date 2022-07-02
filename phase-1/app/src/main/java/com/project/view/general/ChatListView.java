package com.project.view.general;

import com.project.controllers.ChatListController;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

public class ChatListView implements View {

    private static ChatListView instance;
    private ChatListController controller;

    private ChatListView() {
        controller = new ChatListController();
        controller.addAll(User.getCurrentUser().getChats());
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
        printSelections("next", "last", "show -all", "show <chat id>", "top", "open");
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    @Override
    @SuppressWarnings("unchecked")
    public ChatListController getController() {
        return controller;
    }

}