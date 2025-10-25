package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static int[] findMaxOverlapInterval(List intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }

        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(i -> i.start));

        var activeIntervals = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (Interval current : sorted) {
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end < current.start) {
                activeIntervals.poll();
            }

            activeIntervals.add(current);

            if (activeIntervals.size() > maxOverlap) {
                maxOverlap = activeIntervals.size();
                maxStart = current.start;
                maxEnd = current.start;
            }
        }

        return new int[]{
                maxStart, maxEnd
        };
    }

    public static void main(String[] args) {
        List intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
