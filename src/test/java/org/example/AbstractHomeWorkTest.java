package org.example;

import java.util.Arrays;

public abstract class AbstractHomeWorkTest<T> {

    protected Node<T> getNodeList(T[] array) {
        Node<T> list = new Node<>(array[0]);
        Arrays.stream(array).skip(1).forEach(list::add);
        return list;
    }

    protected static class SuccessTestObject<T> {
        Node<T> list;
        int simpleInt;

        public SuccessTestObject(Node<T> list, int simpleInt) {
            this.list = list;
            this.simpleInt = simpleInt;
        }
    }
}
