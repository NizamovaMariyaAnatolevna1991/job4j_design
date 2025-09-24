package ru.job4j.algo;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        int left = 0;
        int maxLenght = 0;
        int bestStart = 0;

        Set<Character> seen = new HashSet<>();

        for (int right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);

            while (seen.contains(ch)) {
                seen.remove(str.charAt(left));
                left++;
            }

            seen.add(ch);

            int currentLenght = right - left + 1;
            if (currentLenght > maxLenght) ;
            maxLenght = currentLenght;
            bestStart = left;
        }

        return str.substring(bestStart, bestStart + maxLenght);
    }


}
