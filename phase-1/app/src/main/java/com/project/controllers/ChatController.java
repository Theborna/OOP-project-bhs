package com.project.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.project.App;
import com.project.models.connection.ChatUserConnection;
import com.project.models.node.Chat;
import com.project.models.node.Message;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.general.ChatView;
import com.project.view.model.MessageView;
import com.project.view.model.PageView;

import static com.project.util.StdOut.*;

public class ChatController implements ListController<MessageView> {

    private List<MessageView> messages = new ArrayList<MessageView>();
    private MessageView currentMsg;
    private int current;
    private boolean showMsg = true;

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        showMsg = true;
        Long inReply = null;
        switch (input) {
            case "n":
            case "next":
            case "scroll down":
            case "down":
            case "d":
                currentMsg = messages.get((++current < messages.size()) ? current : (current = 0));
                break;
            case "l":
            case "last":
            case "scroll up":
            case "up":
            case "u":
                currentMsg = messages.get((--current >= 0) ? current : (current = 0));
                break;
            case "t":
            case "top":
                currentMsg = messages.get(0);
                break;
            case "bottom":
            case "b":
                currentMsg = messages.get(messages.size() - 1);
                break;
            case "like":
                println("added like to message", StdColor.GREEN);
                showMsg = false;
                break;
            case "dislike":
                println("removed like from message", StdColor.GREEN);
                showMsg = false;
                break;
            case "reply":
                inReply = currentMsg.getMessage().getId();
            case "new message":
            case "new":
            case "compose":
                StringBuilder sb = null;
                try {
                    if ((sb = getMsgText(sb)) != null)
                        post(sb, inReply);
                    else
                        println("message canceled", StdColor.YELLOW);

                } catch (changeViewException e) {
                    App.setView(e.getView(), false);
                }
                showMsg = false;
                break;
            case "show -page":
                App.setView(PageView.getInstance().setUser(currentMsg.getMessage().getSender()));
                break;
            case "members":
            case "member":
                showMembers();
                break;
            case "help":
                help();
                break;
            default:
                printError("no such command");
                return;
        }
    }

    private void showMembers() {
        Set<User> users = ChatUserConnection.getUsers(Chat.getCurrent().getId());
        for (User user : users) {
            print(user.toString(), StdColor.WHITE_BOLD_BRIGHT);
            print('|');
        }
    }

    public boolean isShowMsg() {
        return showMsg;
    }

    public StringBuilder getMsgText(StringBuilder msgText) throws changeViewException {
        if (msgText == null)
            msgText = new StringBuilder();
        String input;
        ChatView.promptNewMessage();
        while (!(input = StdIn.nextLine()).equals("")) {
            if (input.equals("-done"))
                break;
            if (input.equals("--cancel"))
                return null;
            msgText.append(input + "\n");
        }
        return msgText;
    }

    public void post(StringBuilder sb, Long inReply) {
        if (sb == null)
            return;
        // TODO: actually post the message
        println("message posted successfully", StdColor.GREEN);

    }

    public MessageView getCurrent() {
        if (currentMsg == null) {
            if (messages.size() != 0)
                currentMsg = messages.get(0);
        }
        return currentMsg;
    }

    public List<MessageView> getChildren() {
        return messages;
    }

    public void clear() {
        messages.clear();
    }

    public void addAll(Collection<Message> messages) {
        this.messages.addAll(messages.stream().map(MessageView::new).toList());
    }

    @Override
    public void help() {
        rule('*');
        print("next, n, down, d, scroll down:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls down to view the next message");
        print("last, l, up, u, scroll up:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls up to view the last message");
        print("top, t:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls to the top of the chat");
        print("bottom, b:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls to the bottom of the chat");
        // print("show -all, all:", StdColor.MAGENTA_UNDERLINED);
        // println(" shows all chat items at once");
        print("new message, new, compose", StdColor.MAGENTA_UNDERLINED);
        println(" compose a new message");
        print("help:", StdColor.MAGENTA_UNDERLINED);
        println(" brings up the help window");
        rule('*');
    }
    //sex

}
