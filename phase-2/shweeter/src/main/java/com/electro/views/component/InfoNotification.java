package com.electro.views.component;

import org.controlsfx.control.Notifications;

import com.electro.App;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import jfxtras.styles.jmetro.Style;

public class InfoNotification {
    private static int notifications;
    static final int NOTIFICATION_LIMIT = 3;

    public InfoNotification(String message) {
        if (notifications >= NOTIFICATION_LIMIT)
            return;
        notifications++;
        Notifications notification = Notifications.create()
                .title("Info")
                .text(message)
                .graphic(new ImageView(App.getImage("images/icons8_info_96px.png")))
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
        if (App.getStyle() == Style.DARK)
            notification.darkStyle();
        notification.show();
        Thread th = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifications--;
        });
        th.start();
    }

}
