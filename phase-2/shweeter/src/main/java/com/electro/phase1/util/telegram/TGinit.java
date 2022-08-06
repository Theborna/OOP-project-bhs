package com.electro.phase1.util.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TGinit {

    private static TG tg = null;

    private TGinit() {
        TelegramBotsApi telegramBotsApi = null;
        TG tgg = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            tgg = new TG();
            telegramBotsApi.registerBot(tgg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        tg = tgg;
    }

    public static TG getInstance() {
        if (tg == null)
            new TGinit();
        return tg;
    }

    public static void main(String[] args) {
        getInstance();
        System.out.println(tg.getBotToken());
        // tg.sendMessage("testing the logger...");
    }

}
