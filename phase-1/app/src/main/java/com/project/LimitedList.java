package com.project;

import java.util.ArrayList;
import java.util.List;

public class LimitedList<E> {

    private int n;
    private List<E> items;
    private E defaultItem;
    private int pos;

    public LimitedList(int n) {
        this.n = n;
        items = new ArrayList<E>(n);
        defaultItem = null;
    }

    public LimitedList<E> setDefault(E item) {
        this.defaultItem = item;
        return this;
    }

    public E get(int index) {
        if (index < 0 || index >= items.size())
            return defaultItem;
        return items.get(index);
    }

    public LimitedList<E> add(E item) {
        if (items.size() + 1 == n)
            items = items.subList(1, items.size());
        items.add(item);
        pos = items.size() - 1;
        return this;
    }

    public int indexOf(E item) {
        return items.indexOf(item);
    }

    public E next() {
        return this.get(--pos);
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
