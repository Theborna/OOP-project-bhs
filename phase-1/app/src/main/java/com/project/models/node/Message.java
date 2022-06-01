package com.project.models.node;

import java.util.ArrayList;

import com.project.models.node.user.User;

import java.sql.Date;

public class Message extends node {
    private StringBuilder text;
    private Image image = null;
    private User sender;
    private int likes;
    private ArrayList<User> Likers;
    private int views;
    private static int PostNum = 0;
    private Chat group;

    public Message(String text, User sender, Chat group) {
        this.text = new StringBuilder(text);
        this.sender = sender;
        this.likes = 0;
        this.Likers = new ArrayList<User>();
        this.views = 0;
        this.group = group;
        setData(PostNum, new Date(1), new Date(2));
        PostNum++;
    }
}