package com.project.view;

import com.project.controllers.Controller;

/**
 * parent class for all view classes
 * @methods show(), getController()
 */
public interface View {
    public void show();

    public <T extends Controller> T getController();
}
