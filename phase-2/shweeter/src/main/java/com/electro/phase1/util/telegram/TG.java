package com.electro.phase1.util.telegram;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.electro.database.PostDB;
import com.electro.database.UserDB;
import com.electro.phase1.AppRegex;
import com.electro.phase1.models.node.post.Post;

import io.github.cdimascio.dotenv.Dotenv;

public class TG extends TelegramLongPollingBot {

    private final String DEF_RESPONSE;
    private final String API_KEY;
    private final String MAIN_CHAT;

    public TG() {
        super();
        Dotenv env = Dotenv.load();
        DEF_RESPONSE = "velam kon lashi from app";
        API_KEY = env.get("CLIENT_API_KEY");
        MAIN_CHAT = env.get("MAIN_CHAT");
    }

    @Override
    public String getBotUsername() {
        return "electronlogsClientbot";
    }

    @Override
    public String getBotToken() {
        return API_KEY;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Matcher m;
        Message message = update.getMessage();
        if ((m = AppRegex.POST_REQUEST.getMatcher(message.getText())) != null) {
            parsePostRequest(update, m);
        } else
            post(update, DEF_RESPONSE);
    }

    private void parsePostRequest(Update update, Matcher m) {
        if (!AppRegex.USERNAME.matches(m.group("username")))
            post(update, "invalid username format");
        else {
            try {
                com.electro.phase1.models.node.user.User user = UserDB.getUserInfo(m.group("username"));
                if (user == null)
                    post(update, "no such user exists");
                else {
                    List<Post> posts = PostDB.getPostsByUS_ID(user.getId());
                    for (int i = 0; i < posts.size() && i < Integer.parseInt(m.group("number")); i++)
                        post(update, posts.get(i).toTGString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void post(Update update, String answer) {
        try {
            execute(new SendMessage(update.getMessage().getChat().getId().toString(), answer));
        } catch (TelegramApiException e) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);// sleep time
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    post(update, answer);
                }
            }).start();
        } finally {
            System.out.println(answer);
        }
    }

    public synchronized void sendMessage(String message) {
        try {
            execute(new SendMessage(MAIN_CHAT, message));
        } catch (TelegramApiException e) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);// sleep time
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    sendMessage(message);
                }
            }).start();
        }
    }
}
