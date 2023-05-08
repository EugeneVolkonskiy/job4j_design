package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, ".txt", p -> p.toFile().getName().endsWith("t.txt")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String extension, Predicate<Path> condition) throws IOException {
        validate(root, extension);
        SearchFiles searcher = new SearchFiles(condition, extension);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(Path validPath, String validExtension) {
        if (!validPath.equals(Path.of("."))) {
            throw new IllegalArgumentException("Root folder is wrong");
        }
        if (!validExtension.endsWith(".txt")) {
            throw new IllegalArgumentException("Extension is not .txt");
        }
    }
}
