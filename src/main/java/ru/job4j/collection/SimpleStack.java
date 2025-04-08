package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private Node<T> head;

    public T pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        T value = current.item;
        head = head.next;
        current.item = null;
        current.next = null;
        return value;
    }

    public void push(T value) {
        head = new Node<>(value, head);
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}


