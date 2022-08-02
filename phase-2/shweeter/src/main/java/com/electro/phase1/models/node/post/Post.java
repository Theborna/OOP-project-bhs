package com.electro.phase1.models.node.post;

import com.electro.phase1.models.node.TextBased;
import com.electro.phase1.models.node.node;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

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

    public Set<Post> getComments() {
        Set<Post> result = new LinkedHashSet<>();
        if (new Random().nextInt() % 2 == 0)
            return result;
        // TODO run a query on the database and get posts;
        result.add(new Post("kos mikham be soorat comment"));
        result.add(new Post(
                "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter."));
        result.add(new Post("vay daram mimiram"));
        for (int i = 0; i < 10; i++) {
            result.add(new Post(String.valueOf(i)));
        }
        return result;
    }
}
