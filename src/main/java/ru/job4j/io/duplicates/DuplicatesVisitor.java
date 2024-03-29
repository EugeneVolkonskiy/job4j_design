package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        map.putIfAbsent(fileProperty, new ArrayList<>());
        map.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void getPaths() {
        map.entrySet().stream()
                .filter(a -> a.getValue().size() > 1)
                .flatMap(it -> it.getValue().stream())
                .forEach(System.out::println);
    }
}