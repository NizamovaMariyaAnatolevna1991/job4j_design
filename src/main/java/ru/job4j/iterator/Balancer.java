package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int currentIndex = 0;
        while (source.hasNext()) {
            nodes.get(currentIndex).add(source.next());
            currentIndex = (currentIndex + 1) % nodes.size();
        }
    }
}
