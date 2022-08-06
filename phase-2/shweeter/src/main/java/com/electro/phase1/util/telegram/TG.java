package com.electro.phase1.util.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.github.cdimascio.dotenv.Dotenv;

public class TG extends TelegramLongPollingBot {

    private String response;
    private final String API_KEY;

    public TG() {
        super();
        response = "velam kon lashi";
        Dotenv env = Dotenv.load();
        API_KEY = env.get("API_KEY");
    }

    @Override
    public String getBotUsername() {
        return "electronlogsbot";
    }

    @Override
    public String getBotToken() {
        return API_KEY;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(new SendMessage(update.getMessage().getChat().getId().toString(), response));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } finally {
            System.out.println(response);
        }
    }

    public synchronized void sendMessage(String message) {
        try {
            execute(new SendMessage("-1001735428152", message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
