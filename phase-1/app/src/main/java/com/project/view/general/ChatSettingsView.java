package com.project.view.general;

import com.project.enums.ChatPermission;
import com.project.models.connection.ChatUserConnection;
import com.project.models.node.Chat;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;

import static com.project.util.StdOut.*;

import java.util.HashMap;
import java.util.Map;

public class ChatSettingsView extends NewChatView {// uses the same controller :)

    public ChatSettingsView() {
        super();
        Map<User, ChatPermission> memberData = ChatUserConnection.getUsers(Chat.getCurrent().getId());
        Map<Long, String> initialMembers = new HashMap<Long, String>();
        Map<String, ChatPermission> initialPerm = new HashMap<String, ChatPermission>();
        for (User u : memberData.keySet()) {
            initialMembers.put(u.getId(), u.getUsername());
            initialPerm.put(u.getUsername(), memberData.get(u));
        }
        controller.setName(Chat.getCurrent().getName()).setLinkID(Chat.getCurrent().getLinkID())
                .setType(Chat.getCurrent().getType())
                .setInitialMembers(initialMembers).setInitialPermissions(initialPerm)
                .setDefaultPermissions();
        finalOutput = "chat edited successfully! ";
        idSetOutput = "enter a new id for the chat or -skip to skip this part, current id: " + controller.getLinkID();
        demand = "enter the new name of the chat or -skip to skip this part, current name: " + controller.getName();
    }

    public static NewChatView getInstance() {
        if (instance == null)
            instance = new ChatSettingsView();
        return instance;
    }

    @Override
    protected void getName() throws changeViewException {
        String name = null;
        while (name == null) {
            prompt(demand);
            String input = StdIn.nextLine().trim();
            if (input.toLowerCase().equals("-skip")) {
                println("skipped", StdColor.YELLOW);
                break;
            }
            name = controller.getChatName(input);
            if (name == null)
                printError("invalid username format");
        }
    }

    @Override
    protected void getType() throws changeViewException {
        // will be empty since we do not want to change the type
    }

}
