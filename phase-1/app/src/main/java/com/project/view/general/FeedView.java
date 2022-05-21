package com.project.view.general;

import java.util.ArrayList;
import java.util.List;

import com.project.controllers.Controller;
import com.project.models.node.Post;
import com.project.util.StdIn;
import com.project.view.View;
import static com.project.util.StdOut.*;

public class FeedView implements View {

    private ArrayList<Post> posts = new ArrayList<Post>(
            List.of(new Post("kos mikham"), new Post("vay daram mimiram"),
                    new Post(
                            "The main reason why System.out.println() can't show Unicode characters is that System.out.println() is a byte stream that deal with only the low-order eight bits of character which is 16-bits. In order to deal with Unicode characters(16-bit Unicode character), you have to use character based stream i.e. PrintWriter.")));

    @Override
    public void show() {
        for (Post post : posts)
            post.showAsView();
        printSelections("scroll up", "scroll down", "show post -id");
        prompt("enter next command");
        StdIn.nextLine();
    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

}
