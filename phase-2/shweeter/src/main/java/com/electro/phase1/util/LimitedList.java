package com.electro.phase1.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.electro.phase1.models.node.Message;

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

    public void addAll(Collection<E> items){
        for(E item : items)
            add(item);
    }
    public  void clear(){
       items.clear();
       pos = 0;
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

    public boolean contains(E item) {
        return items.contains(item);
    }

}
