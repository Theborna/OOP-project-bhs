package com.project.view;

import com.project.view.general.FeedView;
import com.project.view.general.LoginView;
import com.project.view.general.PrimaryView;
import com.project.view.general.RegisterView;
import com.project.view.general.SecondaryView;

public enum ViewsEnum {
    PRIMARY, SECONDARY, LOGIN, HELP, REGISTER, FEED;

    public static View getView(ViewsEnum nextView) {
        switch (nextView) {
            case PRIMARY:
                return new PrimaryView();
            case SECONDARY:
                return new SecondaryView();
            case LOGIN:
                return new LoginView();
            case REGISTER:
                return new RegisterView();
            case FEED:
                return new FeedView();
            case HELP:
                // return new HelpView();
            default: // would not happen but prevents errors
                return null;
        }
    }
}
