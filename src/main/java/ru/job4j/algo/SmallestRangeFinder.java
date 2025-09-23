package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;

public class SmallestRangeFinder {
    /**
     * Добавьте поля класса здесь, если это необходимо
     */

    public static int[] findSmallestRange(int[] nums, int k) {
        int left = 0;
        int minLeft = 0;
        int minRight = nums.length - 1;
        boolean found = false;

        HashMap<Integer, Integer> countMap = new HashMap<>();
        int unique = 0;

        for (int right = 0; right < nums.length; right++) {
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
            if (countMap.get(nums[right]) == 1) {
                unique++;
            }

            while (unique >= k) {
                if (right - left < minRight - minLeft) {
                    minLeft = left;
                    minRight = right;
                    found = true;
                }

                int leftNum = nums[left];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                if (countMap.get(leftNum) == 0) {
                    unique--;
                }
                left++;
            }
        }

        if (found) {
            return new int[]{minLeft, minRight};
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
