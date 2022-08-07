package com.electro.phase1.models.connection;

import com.electro.phase1.models.node.node;

import javafx.scene.Node;

/**
 * 
 */
public abstract class connection<E extends node, T extends node> extends node {

    private E obj1;
    private T obj2;

    public connection(E obj1, T obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.id = PairingFunction(obj1.getId(), obj2.getId());// for correct hashing
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

    public static Long PairingFunction(Long a, Long b) {// HopCroft and Ullman Pairing Function
        if (a == null || b == null)
            return null;
        return (a + b - 2) * (a + b - 1) / 2 + a;
    }

    public static void main(String[] args) { // testing purposes
        int size = 8;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++)
                System.out.print(PairingFunction(Long.valueOf(i), Long.valueOf(j)) + ", ");
            System.out.println();
        }
    }

}
