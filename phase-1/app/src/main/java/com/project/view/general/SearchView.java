package com.project.view.general;

import com.project.controllers.Controller;
import com.project.util.exception.changeViewException;
import com.project.view.View;

public class SearchView implements View {

    private static SearchView instance;

    @Override
    public void show() throws changeViewException {

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
