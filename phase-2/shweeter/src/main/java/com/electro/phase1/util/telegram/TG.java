package com.electro.phase1.util.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.github.cdimascio.dotenv.Dotenv;

public class TG extends TelegramLongPollingBot {

    Dotenv env;

    @Override
    public String getBotUsername() {
        return "electronlogsbot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("API_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("dick");
    }

    public void sendMessage(String message) {
        new Thread(() -> {
            Message msg = new Message();
            msg.setText(message);
            Chat ch = new Chat();
            ch.setId(-1001735428152L);
            msg.setChat(ch);
            try {
                execute(new SendMessage("-1001735428152", message));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
