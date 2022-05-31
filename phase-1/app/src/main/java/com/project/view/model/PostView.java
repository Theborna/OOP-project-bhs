package com.project.view.model;

import com.project.controllers.Controller;
import com.project.models.node.Post;
import com.project.util.StdColor;
import com.project.view.View;

import static com.project.util.StdOut.*;

public class PostView implements View {

    private Post post;

    public PostView(Post post) {
        this.post = post;
    }

    @Override
    public void show() {
        print(post.getSender().getUsername(), StdColor.MAGENTA_UNDERLINED);
        println(" ,date: " + post.getCreationDate() + " ,id: " + post.getId(), StdColor.BLACK_BRIGHT);
        println("\n" + post.getText().toString() + "\n");
        print("likes: ", StdColor.RED_BRIGHT);
        print(post.getLikes() + ", ");
        print("views: ", StdColor.GREEN_BRIGHT);
        print(post.getViews());
        rule();
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


}
