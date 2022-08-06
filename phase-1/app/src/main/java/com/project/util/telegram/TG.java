package com.project.util.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

    public synchronized void sendMessage(String message) {
        // Message msg = new Message();
        // msg.setText(message);
        // Chat ch = new Chat();
        // ch.setId(-1001735428152L);
        // msg.setChat(ch);
        // try {
        //     execute(new SendMessage("-1001735428152", message));
        // } catch (TelegramApiException e) {
        //     e.printStackTrace();
        // }
    }
}
