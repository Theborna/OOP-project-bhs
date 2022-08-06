package com.electro.views.component;

import org.controlsfx.control.Notifications;

import com.electro.App;
import com.electro.database.MessageDB;
import com.electro.phase1.models.connection.MessageConnection;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.Message;

import javafx.geometry.Pos;
import javafx.util.Duration;
import jfxtras.styles.jmetro.Style;

public class MessageNotification {
    private static int notifications;
    static final int NOTIFICATION_LIMIT = 3;

    public MessageNotification(Long chatId) {
        if (Chat.getCurrent().getId() == chatId)
            return;
        if (notifications >= NOTIFICATION_LIMIT)
            return;
        notifications++;
        Message msg = MessageConnection.getLastMessage(chatId);
        if (msg == null)
            return;
        Notifications notification = Notifications.create()
                .title(msg.getCh().getName())
                .text(msg.getAuthor().getName() + ": " + msg.getText())
                .graphic(null)// TODO
                .hideAfter(Duration.seconds(2))
                .position(Pos.TOP_RIGHT);
        if (App.getStyle() == Style.DARK)
            notification.darkStyle();
        notification.show();
        Thread th = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifications--;
        });
        th.start();
    }
}
