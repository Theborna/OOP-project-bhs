package com.view.general;

import com.controllers.Controller;
import com.view.View;

public class HelpView implements View {
    private static HelpView instance;

    private HelpView() {

    }

    public static HelpView getInstance() {
        if (instance == null)
            instance = new HelpView();
        return instance;
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

}