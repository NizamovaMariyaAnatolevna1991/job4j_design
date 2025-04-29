package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.ListIterator;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean result = true;
        if (contains(value)) {
            result = false;
        }
        set.add(value);
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T element : set) {
            if (areEqual(element, value)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    private boolean areEqual(T element, T value) {
        return element == null ? value == null : element.equals(value);
    }
}
