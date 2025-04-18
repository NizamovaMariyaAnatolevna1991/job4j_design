package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.ListIterator;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean exist = false;
        for (T element : set) {
            if (element == null ? value == null : element.equals(value)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T element : set) {
            if (element == null ? value == null : element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
