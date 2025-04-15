package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (isEmpty(output)) {
            while (!isEmpty(input)) {
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
    }

    public boolean isEmpty() {
        return isEmpty(input) && isEmpty(output);
    }

    public boolean isEmpty(SimpleStack<T> stack) {
        try {
            T topElement = stack.pop();
            stack.push(topElement);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}
