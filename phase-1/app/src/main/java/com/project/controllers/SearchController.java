package com.project.controllers;

import com.project.util.exception.changeViewException;

public class SearchController implements Controller{
    @Override
    public void parse(String input) throws changeViewException {

    }

    @Override
    public void help() {

    }

    public int userResult(String username) {
        return 0;
    }

    public int postResult(String post) {
        return 0;
    }

    public int chatResult(String chat) {
        return 0;
    }
}
