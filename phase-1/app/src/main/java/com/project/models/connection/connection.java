package com.project.models.connection;

import com.project.models.node.node;

/**
 * 
 */
public abstract class connection<E, T> extends node {

    private E obj1;
    private T obj2;

    public connection(E obj1, T obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public connection<E, T> setObj1(E obj1) {
        this.obj1 = obj1;
        return this;
    }

    public connection<E, T> setObj2(T obj2) {
        this.obj2 = obj2;
        return this;
    }

    public E getObj1() {
        return obj1;
    }

    public T getObj2() {
        return obj2;
    }

    @Override
    public void sendToDB() {

    }

}
