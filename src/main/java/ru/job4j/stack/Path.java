package ru.job4j.stack;

import java.util.Stack;

/**
 *
 */

public class Path {
    public String simplify(String path) {
        var stack = new Stack<String>();
        var components = path.split("/");

        for (var component : components) {
            if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!component.isEmpty() && !component.equals(".")) {
                stack.push(component);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        var result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Path p = new Path();
        System.out.println(p.simplify("/home/../user"));
        System.out.println(p.simplify("/../"));
        System.out.println(p.simplify("/home//foo/"));
        System.out.println(p.simplify("/a/./b/../../c/"));
    }
}
