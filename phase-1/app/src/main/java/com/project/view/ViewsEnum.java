package com.project.view;

public enum ViewsEnum {
    PRIMARY, SECONDARY, LOGIN, HELP, REGISTER;

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
            case HELP:
                // return new HelpView();
            default: // would not happen but prevents errors
                return null;
        }
    }
}
