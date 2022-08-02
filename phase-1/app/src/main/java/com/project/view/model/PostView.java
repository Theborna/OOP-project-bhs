package com.project.view.model;

import com.project.controllers.Controller;
import com.project.models.connection.Like;
import com.project.models.node.post.Post;
import com.project.models.node.post.PromotedPost;
import com.project.util.StdColor;
import com.project.view.View;

import static com.project.util.StdOut.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PostView implements View {

    private Post post;
    private boolean isSuggestion;

    public PostView(Post post) {
        this.post = post;
    }

    @Override
    public void show() {
        rule('_');
        if (post.getRepliedPost() != null) {
            print("╔════@" + post.getRepliedPost().getSender().getUsername(), StdColor.BLACK_BRIGHT);
            println(": " + post.getRepliedPost().getBuilder(), StdColor.BLACK_BRIGHT);
        }
        print(post.getSender().getUsername(), StdColor.MAGENTA_UNDERLINED);
        println(" ,date: " + post.getCreationDate() + " ,id: " + post.getId(), StdColor.BLACK_BRIGHT);
        if (post instanceof PromotedPost) {
            println("ADVERTISEMENT", StdColor.RED);
        }
        println("\n" + post.getBuilder().toString() + "\n");
        print("likes: ", StdColor.RED_BRIGHT);
        print(post.getLikes() + ", ");
        print("views: ", StdColor.GREEN_BRIGHT);
        print(post.getViews() + ", ");
        print("comments: ", StdColor.CYAN);
        print(post.getCommentsCount());
        rule('_');
    }

    public Post getPost() {
        return post;
    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((post == null) ? 0 : post.hashCode());
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
        PostView other = (PostView) obj;
        if (post == null) {
            if (other.post != null)
                return false;
        } else if (!post.equals(other.post))
            return false;
        return true;
    }

    @Override
    public void reset() {
        return;
    }

    public void showStats() {
        List<Like> result = new ArrayList<Like>();
        result.addAll(Like.getUsers(post));
        Map<Integer, Integer[]> likes = new LinkedHashMap<>();
        likes.put(1, new Integer[] { 2, 1 });
        likes.put(2, new Integer[] { 5, 2 });
        likes.put(3, new Integer[] { 3, 2 });
        likes.put(4, new Integer[] { 7, 4 });
        likes.put(5, new Integer[] { 12, 7 });
        likes.put(6, new Integer[] { 13, 5 });
        likes.put(7, new Integer[] { 2, 1 });
        likes.put(8, new Integer[] { 2, 0 });
        likes.put(9, new Integer[] { 15, 5 });
        likes.put(10, new Integer[] { 30, 5 });
        likes.put(11, new Integer[] { 40, 30 });
        likes.put(12, new Integer[] { 22, 10 });
        // TODO: put last months data into this
        for (Integer[] i : likes.values()) {
            for (int j = 0; j < i[1]; j++)
                print(" ", StdColor.RED_BACKGROUND);
            for (int j = 0; j < i[0] - i[1]; j++)
                print(" ", StdColor.GREEN_BACKGROUND);
            println("");
        }
    }
}
