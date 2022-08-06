package com.electro.phase1.util.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TG extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "electronlogsbot";
    }

    @Override
    public String getBotToken() {
        return "5421036090:AAGFN42FvfONUIX25ZOM4b4_C8vzr9Z9I1s";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("dick");
    }
}
