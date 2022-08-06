package com.electro.phase1.util.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TGinit {

    private static TGinit tg = null;

    private TGinit() {
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TG());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        tg = this;
    }

    public static TGinit getInstance() {
        if (tg == null) {
            tg = new TGinit();
        }
        return tg;
    }

    public void sendMessage(String message) {
        Message msg = new Message();
        msg.setText(message);
        Chat ch = new Chat();
        ch.setId(-1001735428152L);
        msg.setChat(ch);
    }
}
