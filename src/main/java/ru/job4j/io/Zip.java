package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validate(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("The number of parameters must be 3");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("Directory doesn't exist %s", argsName.get("d")));
        }
    }

    public static List<File> getSource(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get("d"));
        String extension = argsName.get("e");
        List<Path> search = Search.search(start, (p -> !p.toFile().getName().endsWith(extension)));
        return search.stream().map(Path::toFile).collect(Collectors.toList());
    }

    public static File getTarget(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        String path = argsName.get("o");
        return new File(path);
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Zip zip = new Zip();
        zip.packFiles(getSource(args), getTarget(args));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
