package com.project.view.model;

import com.project.controllers.Controller;
import com.project.models.connection.Like;
import com.project.models.node.post.Post;
import com.project.models.node.post.PromotedPost;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.format;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserView implements View {

    private User user;
    private boolean isSuggestion;

    public UserView(User user) {
        this.user = user;
    }

    @Override
    public void show() {
        // rule('_');
        // if (post.getRepliedPost() != null) {
        //     print("╔════@" + post.getRepliedPost().getSender().getUsername(), StdColor.BLACK_BRIGHT);
        //     println(": " + post.getRepliedPost().getBuilder(), StdColor.BLACK_BRIGHT);
        // }
        // print(post.getSender().getUsername(), StdColor.MAGENTA_UNDERLINED);
        // println(" ,date: " + format.SimpleDate(post.getCreationDate()) + " ,id: " + post.getId(),
        //         StdColor.BLACK_BRIGHT);
        // if (post instanceof PromotedPost) {
        //     println("ADVERTISEMENT", StdColor.RED);
        // }
        // println("\n" + post.getBuilder().toString() + "\n");
        // print("likes: ", StdColor.RED_BRIGHT);
        // print(post.getLikes() + ", ");
        // print("views: ", StdColor.GREEN_BRIGHT);
        // print(post.getViews() + ", ");
        // print("comments: ", StdColor.CYAN);
        // print(post.getCommentsCount());
        // rule('_');
        System.out.println(user.getUsername());
        //TODO show the users public properties
    }

    public User getUser() {
        return user;
    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int hashCode() {
        //not sure
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserView other = (UserView) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public void reset() {
        return;
    }

    public void showStats() {
        // List<Like> result = new ArrayList<Like>();
        // result.addAll(Like.getUsers(post));
        // Map<Integer, Integer[]> likes = new LinkedHashMap<>();
        // likes.put(1, new Integer[] { 2, 1 });
        // likes.put(2, new Integer[] { 5, 2 });
        // likes.put(3, new Integer[] { 3, 2 });
        // likes.put(4, new Integer[] { 7, 4 });
        // likes.put(5, new Integer[] { 12, 7 });
        // likes.put(6, new Integer[] { 13, 5 });
        // likes.put(7, new Integer[] { 2, 1 });
        // likes.put(8, new Integer[] { 2, 0 });
        // likes.put(9, new Integer[] { 15, 5 });
        // likes.put(10, new Integer[] { 30, 5 });
        // likes.put(11, new Integer[] { 40, 30 });
        // likes.put(12, new Integer[] { 22, 10 });
        // // TODO: put last months data into this
        // for (Integer[] i : likes.values()) {
        //     for (int j = 0; j < i[1]; j++)
        //         print(" ", StdColor.RED_BACKGROUND);
        //     for (int j = 0; j < i[0] - i[1]; j++)
        //         print(" ", StdColor.GREEN_BACKGROUND);
        //     println("");
        // }
        // i guess we don't need this for users
    }
}
