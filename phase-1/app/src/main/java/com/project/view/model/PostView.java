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
        println(" ,date: " + format.SimpleDate(post.getCreationDate()) + " ,id: " + post.getId(),
                StdColor.BLACK_BRIGHT);
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
        User.getCurrentUser().view(post);
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
        for (Like l : result) {
            int s = l.getCreationDate().getDayOfMonth();
            if (!likes.containsKey(s))
                likes.put(s, new Integer[]{1, l.getValue()});
            else {
                Integer[] past = likes.get(s);
                likes.put(s, new Integer[]{past[0] + 1, past[1] + l.getValue()});
            }
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
}
