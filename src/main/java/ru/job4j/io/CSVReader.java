package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Файл не найден: " + path);
        }

        String[] columns = filter.split(",");
        List<Integer> columnIndices = new ArrayList<>();

        String header = lines.get(0);
        String[] headers = header.split(delimiter);

        for (String column : columns) {
            boolean found = false;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(column)) {
                    columnIndices.add(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Столбец '" + column + "' не найден в файле.");
            }
        }

        PrintStream output = null;
        try {
            if ("stdout".equals(out)) {
                output = System.out;
            } else {
                output = new PrintStream(new FileOutputStream(out));
            }

            for (String line : lines) {
                String[] values = line.split(delimiter);
                StringJoiner joiner = new StringJoiner(delimiter);

                for (int index : columnIndices) {
                    joiner.add(values[index]);
                }

                output.println(joiner);
            }
        } finally {
            if (output != null && !output.equals(System.out)) {
                output.close();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Использование: -path=<путь к файлу> -delimiter=<разделитель> -out=<stdout|путь к файлу> -filter=<столбцы>"
            );
        }

        ArgsName argsName = ArgsName.of(args);

        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        if (path.isEmpty() || delimiter.isEmpty() || out.isEmpty() || filter.isEmpty()) {
            throw new IllegalArgumentException("Все параметры должны быть указаны.");
        }

        if (!"-out".equals("stdout") && !new File(out).getParentFile().exists()) {
            throw new IllegalArgumentException("Директория для выходного файла не существует.");
        }

        try {
            handle(argsName);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}