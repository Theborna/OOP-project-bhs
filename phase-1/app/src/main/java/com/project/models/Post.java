package com.project.models;

import com.project.models.user.NormalUser;
import com.project.models.user.User;
import com.project.util.StdColor;

import static com.project.util.StdOut.*;

import java.sql.Date;
import java.util.ArrayList;

public class Post extends data {
    private StringBuilder text;
    private Image image = null;
    private User sender;
    private int likes;
    private ArrayList<User> Likers;
    private int views;
    private static int PostNum = 0;

    public Post(String text) {
        this.text = new StringBuilder(text);
        sender = new NormalUser("borna", "");
        likes = 52;
        views = 146;
        setData(1221513, new Date(1), new Date(2));
    }

    public Post(String text, User sender) {
        this.text = new StringBuilder(text);
        this.sender = sender;
        this.likes = 0;
        this.Likers = new ArrayList<User>();
        this.views = 0;
        setData(PostNum, new Date(1), new Date(2));
        PostNum++;
    }

    public void showAsView() {
        print(sender.getUsername(), StdColor.MAGENTA_UNDERLINED);
        println(" , " + getCreationDate(), StdColor.BLACK_BRIGHT);
        println("\n" + text + "\n");
        print("likes: ", StdColor.RED_BRIGHT);
        print(likes + ", ");
        print("views: ", StdColor.GREEN_BRIGHT);
        print(views);
        rule();
    }

}