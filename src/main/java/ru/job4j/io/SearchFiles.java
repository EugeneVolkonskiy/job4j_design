package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> paths = new ArrayList<>();
    private String extension;

    public SearchFiles(Predicate<Path> condition, String extension) {
        this.condition = condition;
        this.extension = extension;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file) && file.toFile().getName().endsWith(extension)) {
            paths.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() {
        return paths;
    }
}
