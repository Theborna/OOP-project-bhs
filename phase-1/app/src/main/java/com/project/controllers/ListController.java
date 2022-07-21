package com.project.controllers;

import java.util.Collection;

import com.project.models.node.Message;
import com.project.util.exception.changeViewException;

public interface ListController<E> extends Controller { // unused at current state

    public void parse(String input) throws changeViewException;

    public E getCurrent();

    public void clear();

    public void help();

}
