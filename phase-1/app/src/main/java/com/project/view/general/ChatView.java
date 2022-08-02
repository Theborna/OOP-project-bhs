package com.project.view.general;

import com.project.controllers.ChatController;
import com.project.controllers.Controller;
import com.project.enums.ChatPermission;
import com.project.models.connection.MessageConnection;
import com.project.models.node.Chat;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import static com.project.util.StdOut.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatView implements View {

    private static ChatView instance;
    private ChatController controller;

    protected ChatView() {
        controller = new ChatController();
        controller.addAll(MessageConnection.getMessages(Chat.getCurrent().getId()));
        controller.getCurrent();
        controller.setPermission(Chat.getCurrent().getPermission(User.getCurrentUser().getId()));
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
        List<String> selection = new ArrayList<>(Arrays
                .asList(new String[] { "like", "dislike", "forward", "next", "last", "top", "show -page",
                        "members" }));
        switch (controller.getPermission()) {
            case OWNER:
                selection.add("delete");
            case ADMIN:
                selection.add("settings");
            case NORMAL:
                selection.add("new message");
                selection.add("reply");
            case OBSERVER:
            default:
                break;
        }
        if (controller.getCurrent().getMessage().getAuthor().equals(User.getCurrentUser()))
            selection.add("edit");
        printSelections(selection.toArray(new String[selection.size()]));
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
