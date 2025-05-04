package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 0; i <= 9; i++) {
                String line = "1*" + i + "=" + (1 * i);
                output.write(line.getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                PrintWriter output = new PrintWriter(
                        new BufferedOutputStream(
                                new FileOutputStream("data/dataresult.txt")
                        ))) {
            output.println("Hello, world!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
