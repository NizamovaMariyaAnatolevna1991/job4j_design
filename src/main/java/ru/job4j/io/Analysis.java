package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> downtimeRanges = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String line;
            String startDowntime = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+", 2);
                if (parts.length < 2) {
                    continue;
                }

                String status = parts[0];
                String timestamp = parts[1];

                if (("400".equals(status) || "500".equals(status)) && startDowntime == null) {
                    startDowntime = timestamp;
                }

                if (!"400".equals(status) && !"500".equals(status) && startDowntime != null) {
                    downtimeRanges.add(startDowntime + ";" + timestamp);
                    startDowntime = null;
                }
            }

            for (String range : downtimeRanges) {
                writer.println(range);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
