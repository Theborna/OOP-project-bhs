package com.project.view.general;

import com.project.App;
import com.project.AppRegex;
import com.project.controllers.Controller;
import com.project.controllers.SearchController;
import com.project.models.node.Chat;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.View;
import com.project.view.model.PageView;

import java.util.List;
import java.util.regex.Matcher;

import static com.project.util.StdOut.*;

public class SearchView implements View {

    private static SearchView instance;
    private SearchController controller;

    private SearchView() {
        controller = new SearchController();
    }

    @Override
    public void show() throws changeViewException {
        printSelections("-search -user <username>", "-search -chat <chat-name>", "-search -post <post-text>");
        String input = StdIn.nextLine();
        Matcher m;
        if ((m = AppRegex.SEARCH_USER.getMatcher(input)) != null)
            showUserResult(m.group("username"));
        else if ((m = AppRegex.SEARCH_CHAT.getMatcher(input)) != null)
            showChatResult(m.group("chat"));
        else if ((m = AppRegex.SEARCH_POST.getMatcher(input)) != null)
            showPostResult(m.group("post"));
    }

    private void showUserResult(String username) throws changeViewException {
        List<User> result = controller.getUserResult(username);
        if (result == null)
            printError("invalid username format");
        else if (result.size() == 0)
            println("no results found", StdColor.YELLOW);
        else {
            for (User user : result)
                println(user.getUsername(), StdColor.MAGENTA);
            Matcher m;
            boolean running = true;
            while (running) {
                prompt("use \"-show -user <username>\" or -done to go back");
                String input = StdIn.nextLine().trim().toLowerCase();
                if (input.equals("-done"))
                    running = false;
                else if ((m = AppRegex.SHOW_USER.getMatcher(input)) != null) {
                    User user = null;
                    for (User us : result)
                        if (us.getUsername().equals(m.group("username")))
                            user = us;
                    if (user != null) {
                        App.setView(PageView.getInstance().setUser(user));
                        running = false;
                    } else
                        printError("no such user in results");
                } else
                    printError("invalid input");
            }
        }

    }

    private void showChatResult(String chatId) throws changeViewException {
        List<Chat> result = controller.getChatResult(chatId);
        if (result == null)
            printError("invalid username format");
        else if (result.size() == 0)
            println("no results found", StdColor.YELLOW);
        else {
            for (Chat chat : result)
                println(chat.getLinkID(), StdColor.MAGENTA);
            Matcher m;
            boolean running = true;
            while (running) {
                prompt("use \"-show -user <username>\" or -done to go back");
                String input = StdIn.nextLine().trim().toLowerCase();
                if (input.equals("-done"))
                    running = false;
                else if ((m = AppRegex.SHOW_CHAT.getMatcher(input)) != null) {
                    Chat chat = null;
                    for (Chat ch : result)
                        if (ch.getLinkID().equals(m.group("chat")))
                            chat = ch;
                    if (chat != null) {
                        Chat.LogToChat(chat);
                        App.setView(ChatView.getInstance());
                        running = false;
                    } else
                        printError("no such chat in results");
                } else
                    printError("invalid input");
            }
        }
    }

    private void showPostResult(String post) {
        List<Post> result = controller.getPostResult(post);

        if (result == null)
            printError("too long!");
        else if (result.size() == 0)
            println("no results found", StdColor.YELLOW);
        else {
            Matcher m;
            // if((m == AppRegex.))TODO: implement this shit
        }
    }

    public static SearchView getInstance() {
        instance = new SearchView();
        return instance;
    }

    @Override
    public <T extends Controller> T getController() {
        return null;
    }

    @Override
    public void reset() {
        instance = null;
    }
}
