package com.project.view.general;

import java.util.Arrays;
import java.util.Collection;

import com.project.controllers.ForwardController;
import com.project.models.node.Message;
import com.project.models.node.user.User;

import static com.project.util.StdOut.*;

public class ForwardView extends ChatListView {
    private static ForwardView instance;

    private ForwardView(Collection<Message> messages) {
        controller = new ForwardController();
        ((ForwardController) controller).addAll(User.getCurrentUser().getAccessibleChats());
        controller.getCurrent();
        ((ForwardController) controller).setMessages(messages);
    }

    public static ForwardView getInstance(Message... messages) {
        if (instance == null)
            instance = new ForwardView(Arrays.asList(messages));
        return instance;
    }

    @Override
    protected void showSelections() {
        if (((ForwardController) controller).isSelected(controller.getCurrent().getChat()))
            printSelections("deselect", "add description", "confirm");
        else
            printSelections("select", "add description", "confirm");
    }
}
