package com.project.models.connection;

/**
 * 
 */
public abstract class connection<E, T> {

    private E obj1;
    private T obj2;

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

}
