package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map values = new HashMap();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            input.lines()
                    .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("#"))
                    .forEach(line -> {
                        if (!line.contains("=")) {
                            throw new IllegalArgumentException("Invalid format: line does not contain '='");
                        }
                        String[] parts = line.split("=", 2);
                        if (parts[0].trim().isEmpty()) {
                            throw new IllegalArgumentException("Invalid format: key is missing");
                        }
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new IllegalArgumentException("Invalid format: value is missing or empty");
                        }
                        values.put(parts[0].trim(), parts[1].trim());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return (String) values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
