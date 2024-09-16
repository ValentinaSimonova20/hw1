package org.example;

import lombok.Getter;
import lombok.Setter;


/**
 * Реализация базовой структуры односвязного списка
 * При необходимости, можно доработать реализацию
 *
 * @param <T>
 */
@Getter
@Setter
public class Node<T> {

    private T value;

    private int size;

    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.size = 1;
    }

    public Node<T> add(T value) {
        Node<T> emptyNode = this;
        while (emptyNode.getNext() != null) {
            emptyNode = emptyNode.getNext();
        }
        Node<T> newNode = new Node<>(value);
        emptyNode.setNext(newNode);
        size++;
        return newNode;
    }

    public int getSize() {
        return size;
    }

    private void setNext(Node<T> newNode) {
        this.next = newNode;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
