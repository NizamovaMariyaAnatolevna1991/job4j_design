package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        try {
            Path filePath = Paths.get(cachingDir, key);

            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                return null;
            }

            return Files.readString(filePath);
        } catch (IOException e) {
            return null;
        }
    }
}
