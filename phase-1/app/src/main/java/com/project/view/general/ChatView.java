package com.project.view.general;

import com.project.controllers.ChatController;
import com.project.controllers.Controller;
import com.project.models.connection.MessageConnection;
import com.project.models.node.Chat;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import static com.project.util.StdOut.*;

public class ChatView implements View {

    private static ChatView instance;
    private ChatController controller;

    protected ChatView() {
        controller = new ChatController();
        controller.addAll(MessageConnection.getMessages(Chat.getCurrent().getId()));
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
        printSelections("like", "dislike", "forward", "reply", "next", "last", "top", "new message", "show -page",
                "members");
        prompt("enter next command");
        controller.parse(StdIn.nextLine());
    }

    public void askForUse() {

    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    public static void promptNewMessage() {
        rule('_');
        prompt("what's the message?(use \"--cancel\" to stop sending new message)\n");
    }

    @Override
    public void reset() {
        instance = null;
    }
}
