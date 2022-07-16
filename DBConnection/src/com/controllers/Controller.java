package com.controllers;

import com.util.exception.changeViewException;

/**
 * parent class for all controllers
 */
public interface Controller {

    public void parse(String input) throws changeViewException;

    public void help();
}
