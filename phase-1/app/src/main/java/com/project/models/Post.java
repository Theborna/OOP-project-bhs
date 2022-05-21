package com.project.models;

import com.project.models.user.Media;
import com.project.models.user.NormalUser;
import com.project.models.user.User;
import com.project.util.StdColor;

import static com.project.util.StdOut.*;

import java.sql.Date;
import java.util.ArrayList;

public class Post extends data {
    private StringBuilder text;
    private Image image = null;
    private ArrayList<Media> media = new ArrayList<Media>();
    private User sender;
    private int likes;
    private int views;

    public Post(String text) {
        this.text = new StringBuilder(text);
        sender = new NormalUser("borna", "");
        likes = 52;
        views = 146;
        setData(1221513, new Date(1), new Date(2));
    }

    public Post(String text,User Sender) {
        this.text = new StringBuilder(text);
        sender = Sender;
        likes = 0;
        views = 0;
        setData(1221513, new Date(1), new Date(2));
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
