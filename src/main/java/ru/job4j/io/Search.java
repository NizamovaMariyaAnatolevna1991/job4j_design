package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        String extention = args[1];
        List<Path> result = search(start, path -> path.toFile().getName().endsWith(extention));
        result.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Неверное количество параметров. Используйте: <путь> <расширение>");
        }

        Path start = Paths.get(args[0]);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException("Указанный путь не существует: " + start.toAbsolutePath());
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("Указанный путь не является директорией: " + start.toAbsolutePath());
        }

        String extension = args[1];
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("Расширение должно начинаться с точки, например: '.js'");
        }
    }
}
