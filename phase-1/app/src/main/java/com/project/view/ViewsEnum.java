package com.project.view;

public enum ViewsEnum {
    PRIMARY, SECONDARY, LOGIN;

    public static View getView(ViewsEnum nextView) {
        switch (nextView) {
            case PRIMARY:
                return new PrimaryView();
            case SECONDARY:
                return new SecondaryView();
            case LOGIN:
                return new LoginView();
            default: // would not happen but prevents errors
                return null;
        }
    }
}
