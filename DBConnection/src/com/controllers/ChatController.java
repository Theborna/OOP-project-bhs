package com.controllers;

import com.App;
import com.models.node.Message;
import com.util.StdColor;
import com.util.StdIn;
import com.util.exception.changeViewException;
import com.view.general.ChatView;
import com.view.model.MessageView;
import com.view.model.PageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.util.StdOut.*;

public class ChatController implements ListController<MessageView> {

    private List<MessageView> messages = new ArrayList<MessageView>();
    private MessageView currentMsg;
    private int current;
    private boolean showMsg = true;

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        showMsg = true;
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
            case "new message":
            case "new":
            case "compose":
                StringBuilder sb = null;
                try {
                    if (getMsgText(sb))
                        post(sb);
                } catch (changeViewException e) {
                    App.setView(e.getView(), false);
                }
                showMsg = false;
                break;
            case "show -page":
                App.setView(PageView.getInstance().setUser(currentMsg.getMessage().getSender()));
                break;
            case "help":
                help();
                break;
            default:
                printError("no such command");
                return;
        }
    }

    public boolean isShowMsg() {
        return showMsg;
    }

    public boolean getMsgText(StringBuilder msgText) throws changeViewException {
        if (msgText == null)
            msgText = new StringBuilder();
        String input;
        ChatView.promptNewMessage();
        while (!(input = StdIn.nextLine()).equals("")) {
            if (input.equals("-done"))
                break;
            msgText.append(input + "\n");
        }
        return true;
    }

    public void post(StringBuilder sb) {
        // TODO: actually post the message
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

}