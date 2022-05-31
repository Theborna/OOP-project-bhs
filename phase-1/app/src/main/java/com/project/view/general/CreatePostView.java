package com.project.view.general;

import com.project.controllers.Controller;
import com.project.controllers.CreatePostController;
import com.project.models.node.user.User;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import static com.project.util.StdOut.*;

import com.project.App;

public class CreatePostView implements View {

    private StringBuilder postText = new StringBuilder();
    private CreatePostController controller = new CreatePostController();

    @Override
    public void show() throws changeViewException {
        while (true) {
            prompt("What's happening?");
            print("\n");
            if (controller.getPostText(postText))
                break;
        }
        System.out.println(postText);
        App.setView(SecondaryView.getInstance());
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

}
