package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleStack;

import java.util.NoSuchElementException;

public class SimpleQueue<T> implements Queue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int out = 0;
    private int in = 0;

    @Override
    public void push(T value) {
        input.push(value);
        in++;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (out == 0) {
            while (in > 0) {
                output.push(input.pop());
                in--;
                out++;
            }
        }
        T result = output.pop();
        out--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return in == 0 && out == 0;
    }
}
