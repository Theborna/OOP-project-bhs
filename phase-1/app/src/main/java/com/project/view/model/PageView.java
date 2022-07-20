package com.project.view.model;

import com.project.controllers.FeedController;
import com.project.controllers.PageController;
import com.project.models.node.user.BusinessUser;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;
import com.project.util.StdColor;
import com.project.util.StdIn;
import com.project.util.exception.changeViewException;
import com.project.view.general.FeedView;

import static com.project.util.StdOut.*;

public class PageView extends FeedView {

    private static PageView instance;
    private User user;
    private boolean gaveBasic;

    private PageView() {
        controller = new PageController();
    }

    public static PageView getInstance() {
        if (instance == null)
            instance = new PageView();
        return instance;
    }

    public PageView setUser(User user) {
        this.user = user;
        initialize();
        return this;
    }

    private void initialize() {
        if (user == null)
            return;
        controller.clear();
        controller.addAll(user.getPosts());
        gaveBasic = false;
    }

    @Override
    public void show() throws changeViewException {
        if (((PageController) controller).isNeedInfo())
            gaveBasic = false;
        if (!gaveBasic) {
            info();
            int ans = 0;
            do {
                println("");
                printSelections("Y", "N");
                prompt("continue?");
            } while ((ans = ((PageController) controller).isContinue(StdIn.nextLine())) == 3);
            if (ans == 2)
                return;
            gaveBasic = true;
        }
        super.show();
    }

    private void info() { // TODO make this prettier
        rule('_');
        print("username:", StdColor.MAGENTA_UNDERLINED);
        print(" " + user.getUsername());
        println("\t" + user.getId(), StdColor.BLACK_BRIGHT);
        print("followers:", StdColor.RED);
        println(" " + user.getFollowerCnt());
        print("account type:", StdColor.CYAN);
        println(" " + ((user.isPublic() ? "public" : "private")) + " "
                + ((user instanceof NormalUser) ? "basic" : "business") + " account");
        print("total karma:", StdColor.GREEN);
        println(" shayad karma bezarim");
        if (user instanceof BusinessUser)
            println(user.getUsername() + " works at " + ((BusinessUser) user).getBusinessType());
    }

    @Override
    protected void printCommands() {
        printSelections("scroll up", "scroll down", "show post -id", "top", "like", "dislike", "info");
    }

    @Override
    @SuppressWarnings("unchecked")
    public FeedController getController() {
        return controller;
    }

}