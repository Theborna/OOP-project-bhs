package com.project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.project.App;
import com.project.AppRegex;
import com.project.models.node.Chat;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.exception.changeViewException;

public class SearchController implements Controller {

    private List<User> userResult;
    private List<Post> postResult;
    private List<Chat> chatResult;

    @Override
    public void parse(String input) throws changeViewException {

    }

    @Override
    public void help() {

    }

    public List<User> getUserResult(String username) {
        if (!AppRegex.USERNAME.matches(username))
            return null;
        userResult = new ArrayList<User>();// TODO:implement
        return userResult;
    }

    public List<Chat> getChatResult(String chat) {
        if (!AppRegex.CHAT_ID.matches(chat))
            return null;
        chatResult = new ArrayList<Chat>();// TODO:implement
        return chatResult;
    }

    public List<Post> getPostResult(String post) {
        return postResult;
    }

}
