package com.project;

import java.util.ArrayList;

public class LimitedList<E> {

    private int n;
    private ArrayList<E> items;

    public LimitedList(int n) {
        this.n = n;
        items = new ArrayList<E>(n);
    }



}
