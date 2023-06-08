package ru.job4j.io.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SearchFiles {

    public static void validate(String directory, String nameMask, String searchType, String output) {
        if (!Files.isDirectory(Path.of(directory))) {
            throw new IllegalArgumentException(String.format("Not directory %s", directory));
        }
        if (!(nameMask.contains(".") && nameMask.length() > 2)) {
            throw new IllegalArgumentException(String.format("File extension must start with '.', and file's name length must be more than 2, %s", nameMask));
        }
        if (!(searchType.equals("name") || searchType.equals("mask"))) {
            throw new IllegalArgumentException(String.format("Search type must be 'name' or 'mask', %s", searchType));
        }
        if (!Files.exists(Path.of(output))) {
            throw new IllegalArgumentException(String.format("Output file doesn't exist, %s", output));
        }
    }

    public static List<Path> searchResult(String directory, String searchType, String name) throws IOException {
        List<Path> result = new ArrayList<>();
        if (searchType.equals("name")) {
           result = Search.search(Path.of(directory), (p -> p.toFile().getName().equals(name)));
        }
        if (searchType.equals("mask")) {
            String mask = name.replaceAll("\\*", "\\\\w+").replaceAll("\\?", "\\\\w");
            result = Search.search(Path.of(directory), (p -> p.toFile().getName().matches(mask)));
        }
        return result;
    }

    public void write(List<Path> paths, File target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path p : paths) {
                out.println(p.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("The number of parameters must be 4");
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String name = argsName.get("n");
        String searchType = argsName.get("t");
        String output = argsName.get("o");
        validate(directory, name, searchType, output);
        SearchFiles file = new SearchFiles();
        file.write(searchResult(directory, searchType, name), new File(output));
    }
}
