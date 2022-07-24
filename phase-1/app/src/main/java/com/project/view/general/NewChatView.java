package com.project.view.general;

import com.project.controllers.ChatListController;
import com.project.controllers.Controller;
import com.project.controllers.NewChatController;
import com.project.models.node.Chat;
import com.project.models.node.ChatType;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.util.ArrayList;
import java.util.List;

import com.project.App;

public class NewChatView implements View {
    private static NewChatView instance;
    private NewChatController controller;

    private NewChatView() {
        controller = new NewChatController();
    }

    public static NewChatView getInstance() {
        if (instance == null)
            instance = new NewChatView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        String name = null;
        ChatType type = null;
        // Chat chat = new Chat(newMemberName, null);
        while (name == null) {
            prompt("enter the name of the chat");
            name = controller.getChatName(StdIn.nextLine());
            if (name == null)
                printError("invalid username format");
        }
        while (type == null) {
            printSelections("private", "channel", "group");
            prompt("enter type");
            type = controller.getChatType(StdIn.nextLine());
            if (type == null)
                printError("invalid chat type");
        }
        Chat chat = new Chat(name, type);
        List<Long> members = null;
        List<String> names = new ArrayList<>();
        do {
            members = getMembers(type, names);
            if (members.size() == 0)
                printError("no members in chat!");
        } while (members.size() == 0);
        println("got all the members!", StdColor.GREEN);
        chat.addAll(members);
        println("chat was made successfully! ", StdColor.GREEN);
        println("name: " + name);
        println("members: ");
        print(names, StdColor.MAGENTA);
        // TODO: add the chat to DB
        chat.sendToDB();
        App.setView(ChatListView.getInstance());
    }

    private List<Long> getMembers(ChatType type, List<String> names) throws changeViewException {
        println("enter the usernames of the members u want to add" + "(-done to stop and --reset to reset)",
                StdColor.CYAN);
        String newMemberName = null;
        List<Long> members = new ArrayList<Long>();
        Long id = null;
        while (newMemberName == null || id == null) {
            prompt("enter a new members name");
            String input = StdIn.nextLine();
            newMemberName = controller.getUsername(input);
            if (input.equals("-done"))
                break;
            if (input.equals("--reset")) {
                names.clear();
                return getMembers(type, names);
            }
            if (newMemberName == null)
                printError("invalid username format");
            else if ((id = User.getID(newMemberName)) == null)
                printError("no such user available");
            else {
                members.add(id);
                names.add(newMemberName);
                id = null;
                if (type == ChatType.PRIVATE)
                    break;
            }
        }
        return members;
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
