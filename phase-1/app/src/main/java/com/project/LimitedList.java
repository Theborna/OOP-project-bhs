package com.project;

import java.util.ArrayList;
import java.util.List;

public class LimitedList<E> {

    private int n;
    private List<E> items;
    private E defaultItem;

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
        if (index < 0 || index >= n)
            return defaultItem;
        return items.get(index);
    }

    public LimitedList<E> add(E item) {
        if (items.size() + 1 == n)
            items = items.subList(1, items.size());
        items.add(item);
        return this;
    }

    public int indexOf(E item) {
        return items.indexOf(item);
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
