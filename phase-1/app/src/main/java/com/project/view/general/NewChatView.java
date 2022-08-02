package com.project.view.general;

import com.project.controllers.Controller;
import com.project.controllers.NewChatController;
import com.project.enums.ChatPermission;
import com.project.enums.ChatType;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;

import static com.project.util.StdOut.*;

import com.project.App;

public class NewChatView implements View {
    protected static NewChatView instance;
    protected NewChatController controller;
    protected String finalOutput;
    protected String idSetOutput;
    protected String demand;

    protected NewChatView() {
        controller = new NewChatController();
        finalOutput = "chat was made successfully! ";
        idSetOutput = "and finally enter an id for the chat or -none to make chat private";
        demand = "enter the name of the chat";
    }

    public static NewChatView getInstance() {
        if (instance == null)
            instance = new NewChatView();
        return instance;
    }

    @Override
    public void show() throws changeViewException {
        // Chat chat = new Chat(newMemberName, null);
        try {
            getType();
            getName();
            getMembers();
            println("got all the members!", StdColor.GREEN);
            changePermissions();
            println("permissions changed!", StdColor.GREEN);
            getUsername();
            println(finalOutput, StdColor.GREEN);
            println("name: " + controller.getName());
            println("members: ");
            print(controller.getMembers().values(), StdColor.MAGENTA);
            controller.makeChat();
            instance = null;
            App.setView(ChatListView.getInstance());
        } catch (changeViewException e) {
            instance = null;
            throw e;
        }
    }

    protected void getUsername() throws changeViewException {
        if (controller.getType() == ChatType.PRIVATE)
            return;
        println(idSetOutput);
        boolean running = true;
        while (running)
            switch (controller.getId(StdIn.nextLine())) {
                case 0:
                    printError("chat already exists");
                    break;
                case 1:
                    printError("invalid chat id format");
                    break;
                case 3:
                    println("skipped", StdColor.YELLOW);
                    running = false;
                    break;
                case 5:
                    println("chat set as private", StdColor.GREEN);
                case 2:
                    running = false;
                    break;
                default:
                    break;
            }
    }

    private void changePermissions() throws changeViewException {
        println("change permissions of those u want(-done to stop)", StdColor.CYAN);
        String[] selections = new String[ChatPermission.values().length];
        for (int i = 0; i < selections.length; i++)
            selections[i] = ChatPermission.values()[i].name().toLowerCase();
        int ans = -1;// arbitrary number that will result in the default case
        do {
            println("current permissions: "
                    + controller.getPermissions().toString().replaceAll("=", ": ").toLowerCase(), StdColor.MAGENTA);
            if (controller.getType() == ChatType.PRIVATE)
                return;
            switch (ans) {
                case 0:
                    return;
                case 1:
                    printError("invalid input format");
                    break;
                case 2:
                    printError("no such member in chat");
                    break;
                case 3:
                    printError("invalid permission format");
                    break;
                case 4:
                    println("permission changed", StdColor.GREEN);
                    break;
                case 5:
                    printError("cannot change the role of the owner");
                    break;
                default:
                    break;
            }
            println("use -permission <username> <permission> to change permissions");
            print("for <permission> ", StdColor.CYAN);
            printSelections(selections);
            prompt("enter command");
        } while ((ans = controller.changePermission(StdIn.nextLine())) != 0);

    }

    protected void getName() throws changeViewException {

        String name = null;
        while (name == null) {
            prompt(demand);
            name = controller.getChatName(StdIn.nextLine());
            if (name == null)
                printError("invalid username format");
        }
    }

    protected void getType() throws changeViewException {
        ChatType type = null;
        while (type == null) {
            printSelections("private", "channel", "group");
            prompt("enter type");
            type = controller.getChatType(StdIn.nextLine());
            if (type == null)
                printError("invalid chat type");
        }
    }

    private void getMembers() throws changeViewException {
        println("enter the usernames of the members u want to add/remove" + "(-done to stop and -reset to reset)",
                StdColor.CYAN);
        boolean running = true;
        while (running) {
            println("current members: " + controller.getMembers().values(), StdColor.MAGENTA);
            println("use \"-add <username>\" to add members and \"-remove <username>\" to remove members");
            prompt("enter command");
            switch (controller.parseMember(StdIn.nextLine())) {
                case 0:
                    printError("invalid input");
                    break;
                case 1:
                    println("added successfully", StdColor.GREEN);
                    break;
                case 2:
                    println("removed successfully", StdColor.GREEN);
                    break;
                case 3:
                    println("no such user in chat", StdColor.RED);
                    break;
                case 4:
                    running = false;
                    break;
                case 5:
                    println("reset...", StdColor.YELLOW);
                    break;
                case 6:
                    printError("can't add more users");
                    break;
                case 7:
                    printError("no such user exists!");
                    break;
                case 8:
                    printError("cannot create an empty chat!");
                    break;
                default:
                    break;
            }
        }
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
