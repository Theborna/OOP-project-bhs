package com.project.models.node.post;

import com.database.PostDB;
import com.database.viewDB;
import com.project.models.node.Media;
import com.project.models.node.TextBased;
import com.project.models.node.node;
import com.project.models.node.user.User;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Post extends node implements TextBased {
    private static long PostId;
    private StringBuilder text;
    private User sender;
    private int likes;
    private int views;
    private int comments;
    private Post repliedPost;
    private Media md;

    public Post(String text, User Sender) {
        this.text = new StringBuilder(text);
        sender = Sender;

    }

    public Media getMd() {
        return md;
    }

    public void setMd(Media md) {
        this.md = md;
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
    }

    public User getSender() {
        return sender;
    }

    public int getLikes() {
        try {
            return viewDB.getLikesCount(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getViews() {
        try {
            return viewDB.getViewsCount(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCommentsCount() {
        try {
            return PostDB.getComments(id).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setComments(int comments) {
        this.comments = comments;
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

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        try {
            PostDB.addToDB(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Post> getComments() {
        Set<Post> result = new LinkedHashSet<>();
        try {
            result.addAll(PostDB.getComments(this.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
