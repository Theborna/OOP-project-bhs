package com.project.controllers;

public interface ListController<E> { // unused at current state

    public void parse(String input);

    public E getCurrent();

    public void clear();

}
