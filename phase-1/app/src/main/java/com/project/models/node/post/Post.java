package com.project.models.node.post;

import com.project.models.node.Media;
import com.project.models.node.node;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Post extends node {
    private static long PostId;
    private StringBuilder text;
    private ArrayList<Media> media = new ArrayList<Media>();
    private User sender;
    private int likes;
    private int views;

    public Post(String text) {
        this.text = new StringBuilder(text);
        sender = new NormalUser("borna", "");
        likes = 52;
        views = 146;
        setData(PostId++, new Date(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                new Date(2).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public Post(String text, User Sender) {
        this.text = new StringBuilder(text);
        sender = Sender;
        likes = 0;
        views = 0;
        setData(PostId++, new Date(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                new Date(2).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public User getSender() {
        return sender;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public StringBuilder getText() {
        return text;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
