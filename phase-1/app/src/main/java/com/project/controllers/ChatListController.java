package com.project.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.project.App;
import com.project.models.node.Chat;
import com.project.util.StdColor;
import com.project.util.exception.changeViewException;
import com.project.view.general.ChatView;
import com.project.view.model.ChatItemView;
import static com.project.util.StdOut.*;

public class ChatListController implements ListController<ChatItemView> {

    private List<ChatItemView> chatItems = new ArrayList<ChatItemView>();
    private ChatItemView currentChat;
    private int current;

    @Override
    public void parse(String input) {
        input = input.toLowerCase().trim();
        switch (input) {
            case "n":
            case "next":
            case "scroll down":
            case "down":
            case "d":
                currentChat = chatItems.get((++current < chatItems.size()) ? current : (current = 0));
                break;
            case "l":
            case "last":
            case "scroll up":
            case "up":
            case "u":
                currentChat = chatItems.get((--current >= 0) ? current : (current = 0));
                break;
            case "t":
            case "top":
                currentChat = chatItems.get(0);
                break;
            case "all":
            case "show -all":
                showAll();
                break;
            case "help":
                help();
                break;
            case "open":
                Chat.LogToChat(currentChat.getChat().getId());
                App.setView(ChatView.getInstance());
                break;
            default:
                printError("no such command");
                break;
        }
    }

    private void showAll() {
        for (ChatItemView item : chatItems)
            try {
                item.show();
            } catch (changeViewException e) {
                e.printStackTrace();
            }
    }

    public ChatItemView getCurrent() {
        if (currentChat == null)
            if (chatItems.size() != 0)
                currentChat = chatItems.get(0);
        return currentChat;
    }

    public List<ChatItemView> getChildren() {
        return chatItems;
    }

    public void clear() {
        chatItems.clear();
    }

    public void addAll(Collection<Chat> chats) {
        chatItems.addAll(chats.stream().map(ChatItemView::new).toList());
    }

    @Override
    public void help() {
        // TODO : implement help
        rule('*');
        print("next, n, down, d, scroll down:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls down to view the next chat item");
        print("last, l, up, u, scroll up:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls up to view the last chat item");
        print("top, t:", StdColor.MAGENTA_UNDERLINED);
        println(" scrolls to the top of the chat list");
        print("show -all, all:", StdColor.MAGENTA_UNDERLINED);
        println(" shows all chat items at once");
        print("help:", StdColor.MAGENTA_UNDERLINED);
        println(" brings up the help window");
        rule('*');
    }

}
