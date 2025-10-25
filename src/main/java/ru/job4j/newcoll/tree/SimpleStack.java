package ru.job4j.newcoll.tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private final ArrayList<T> stack = new ArrayList<>();

    public void push(T value) {
        stack.add(value);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
