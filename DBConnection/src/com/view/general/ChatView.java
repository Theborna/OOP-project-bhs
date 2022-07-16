package com.view.general;

import com.controllers.ChatController;
import com.controllers.Controller;
import com.models.connection.MessageConnection;
import com.models.node.Chat;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

public class ChatView implements View {

    private static ChatView instance;
    private ChatController controller;

    private ChatView() {
        controller = new ChatController();
        controller.addAll(MessageConnection.getMessages(Chat.getCurrent()));
        controller.getCurrent();
    }

    public static ChatView getInstance() {
        if (instance == null)
            instance = new ChatView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        if (controller.isShowMsg())
            controller.getCurrent().show();
        printSelections("like", "dislike", "reply", "next", "last", "top", "new message" , "show -page");
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    public static void promptNewMessage() {
        rule('_');
        prompt("what's the message?\n");
    }

}
