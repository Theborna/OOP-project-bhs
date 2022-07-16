package com.view.general;

import com.controllers.ChatListController;
import com.models.node.user.User;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.printSelections;
import static com.util.StdOut.prompt;


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