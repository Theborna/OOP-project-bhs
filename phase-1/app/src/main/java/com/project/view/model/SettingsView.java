package com.project.view.model;

import com.project.controllers.Controller;
import com.project.util.exception.changeViewException;
import com.project.view.View;

public class SettingsView implements View {

    private static SettingsView instance;

    private SettingsView() {

    }

    public static SettingsView getInstance() {
        if (instance == null)
            instance = new SettingsView();
        return null;
    }

    @Override
    public void show() throws changeViewException {
        // TODO Auto-generated method stub

    }

    @Override
    public <T extends Controller> T getController() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reset() {
        return;
    }
}
