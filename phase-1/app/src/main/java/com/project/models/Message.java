package com.project.models;

import java.util.ArrayList;

import com.project.models.user.NormalUser;
import com.project.models.user.User;
import com.project.util.StdColor;
import com.project.models.data;
import com.project.models.Chat;
import java.sql.Date;


public class Message extends data {
        private StringBuilder text;
        private Image image = null;
        private User sender;
        private int likes;
        private ArrayList<User> Likers;
        private int views;
        private static int PostNum = 0;
        private Chat group;

        public Message(String text,User sender,Chat group){
        this.text = new StringBuilder(text);
        this.sender=sender;
        this.likes = 0;
        this.Likers= new ArrayList<User>();
        this.views = 0;
        this.group = group;
        setData(PostNum, new Date(1), new Date(2));
        PostNum++;
        }
    }