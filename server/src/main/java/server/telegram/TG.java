package server.telegram;

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
    private final String MAIN_CHAT;

    public TG() {
        super();
        response = "velam kon lashi from server";
        Dotenv env = Dotenv.load();
        API_KEY = env.get("API_KEY");
        MAIN_CHAT = env.get("MAIN_CHAT");
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

    public void sendMessage(String message) {
        Message msg = new Message();
        msg.setText(message);
        Chat ch = new Chat();
        ch.setId(-1001735428152L);
        msg.setChat(ch);
        try {
            execute(new SendMessage(MAIN_CHAT, message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
