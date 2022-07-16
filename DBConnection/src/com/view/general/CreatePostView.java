package com.view.general;

import com.App;
import com.controllers.Controller;
import com.controllers.CreatePostController;
import com.util.exception.changeViewException;
import com.view.View;

import static com.util.StdOut.print;
import static com.util.StdOut.prompt;

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
