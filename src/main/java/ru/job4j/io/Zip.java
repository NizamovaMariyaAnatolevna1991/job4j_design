package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                ZipEntry entry = new ZipEntry(source.toString());
                zip.putNextEntry(entry);
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(input.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: -d=<directory> -e=<exclude_extension> -o=<output_zip>");
        }

        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");

        Path startDir = Paths.get(directory);
        if (!startDir.toFile().exists() || !startDir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist or is not a directory: " + directory);
        }

        List<Path> filesToArchive = Search.search(
                startDir,
                path -> !path.toFile().getName().endsWith(exclude)
        );

        File target = new File(output);
        new Zip().packFiles(filesToArchive, target);

        System.out.println("Archiving completed successfully: " + target.getAbsolutePath());
    }
}
