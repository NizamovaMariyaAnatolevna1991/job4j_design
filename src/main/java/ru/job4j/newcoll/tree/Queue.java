package ru.job4j.newcoll.tree;

public interface Queue<T> {
    void push(T value);

    T poll();

    boolean isEmpty();
}
