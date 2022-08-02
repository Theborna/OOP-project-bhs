package com.electro.phase1.models.node.post;

import com.electro.phase1.models.node.TextBased;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;

import java.time.LocalDateTime;

public class Post extends node implements TextBased {
    private static long PostId;
    private StringBuilder text;
    private User sender;
    private int likes;
    private int views;

    public Post(String text) {
        this.text = new StringBuilder(text);
        sender = new NormalUser("borna", "");
        likes = 52;
        views = 146;
        setData(PostId++, LocalDateTime.now(),
                LocalDateTime.now());
    }

    public Post(String text, User Sender) {
        this.text = new StringBuilder(text);
        sender = Sender;
        likes = 0;
        views = 0;
        setData(PostId++, LocalDateTime.now(),
                LocalDateTime.now());
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

    public StringBuilder getBuilder() {
        return text;
    }

    @Override
    public String getText() {
        return text.toString();
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
