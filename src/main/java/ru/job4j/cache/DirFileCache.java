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
    protected String load(String key)  {
        System.out.println("читаю файл " + key);
        Path path = Paths.get(String.format("%s%s", cachingDir, key));
        String value = "";
        try {
            value = Files.readString(path);
        } catch (IOException e) {
            System.out.println("Указанный файл не существует: " + e.getMessage() + System.lineSeparator());
        }
        return value;
    }
}
