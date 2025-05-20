package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path startDir = Path.of("./");
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(startDir, visitor);
        visitor.printDublicates();
    }
}
