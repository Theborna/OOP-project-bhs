package com.project.models.node.post;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.node.Image;
import com.project.models.node.Media;
import com.project.models.node.node;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

import java.sql.Date;
import java.util.ArrayList;

public class Post extends node {
    private static long PostId;
    private StringBuilder text;
    private Image image = null;
    private ArrayList<Media> media = new ArrayList<Media>();
    private User sender;
    private int likes;
    private int views;
    private int comments;
    private Post repliedPost;

    public Post(String text) {
        this.text = new StringBuilder(text);
        sender = new NormalUser("borna", "");
        likes = 52;
        views = 146;
        // setData(PostId++, new Date(1), new Date(2));
    }

    public Post getRepliedPost() {
        return repliedPost;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setRepliedPost(Post repliedPost) {
        this.repliedPost = repliedPost;
        comments = 10;
        setData(PostId++, LocalDateTime.now(),
                LocalDateTime.now());
    }

    public Post(String text, User Sender) {
        this.text = new StringBuilder(text);
        sender = Sender;
        likes = 0;
        views = 0;
        // setData(PostId++, new Date(1), new Date(2));
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

    public int getCommentsCount() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
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

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub

    }

    public Set<Post> getComments() {
        Set<Post> result = new LinkedHashSet<>();
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
