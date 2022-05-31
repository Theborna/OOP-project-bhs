package com.project.view;

import com.project.controllers.Controller;
import com.project.util.exception.changeViewException;

/**
 * parent class for all view classes
 * @methods show(), getController()
 */
public interface View {
    public void show() throws changeViewException;

    public <T extends Controller> T getController();
}
