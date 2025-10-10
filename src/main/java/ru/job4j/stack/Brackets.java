package ru.job4j.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Brackets {

    private static final Map<Character, Character> BRACKETS = new HashMap<>();

    static {
        BRACKETS.put('(', ')');
        BRACKETS.put('{', '}');
        BRACKETS.put('[', ']');
    }

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (BRACKETS.containsKey(c)) {
                stack.push(c);
            } else if (BRACKETS.containsValue(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                System.out.println(open);
                char expectedClose = BRACKETS.get(open);
                if (expectedClose != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
