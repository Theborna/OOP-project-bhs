package com.view.model;


import com.controllers.Controller;
import com.models.connection.MessageConnection;
import com.models.node.Chat;
import com.models.node.Message;
import com.util.StdColor;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.*;

public class ChatItemView implements View {

    private Chat chat;

    public ChatItemView(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    @Override
    public void show() throws changeViewException {
        rule('_');
        print(chat.getType() + ": ", StdColor.GREEN_BOLD);
        println(chat.getName(), StdColor.MAGENTA_UNDERLINED);
        println("last active: " + chat.getLastModifiedDate() + ", id: " + chat.getId(), StdColor.BLACK_BRIGHT);
        Message last = MessageConnection.getLastMessage(chat);
        String shownText = last.getMessage().toString();
        if (last.getMessage().length() > 30)
            shownText = last.getMessage().substring(0, 60) + "...";
        println("╚═══@" + last.getSender().getUsername() + ": " + shownText, StdColor.BLACK_BOLD_BRIGHT);
        rule('_');
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chat == null) ? 0 : chat.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatItemView other = (ChatItemView) obj;
        if (chat == null) {
            if (other.chat != null)
                return false;
        } else if (!chat.equals(other.chat))
            return false;
        return true;
    }

}
