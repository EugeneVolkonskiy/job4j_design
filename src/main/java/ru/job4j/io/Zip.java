package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
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

    public static void validate(String directory, String exclude, String output) {
        if (!Files.isDirectory(Path.of(directory))) {
            throw new IllegalArgumentException(String.format("Directory %s doesn't exist", directory));
        }
        if (!(exclude.startsWith(".") && exclude.length() > 1)) {
            throw new IllegalArgumentException(String.format("File %s extension must start with '.', and it's length must be more than 1", exclude));
        }
        if (!(output.endsWith(".zip"))) {
            throw new IllegalArgumentException(String.format("File %s extension must be '.zip'", output));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("The number of parameters must be 3");
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        validate(directory, exclude, output);
        List<Path> sourceList = Search.search(Path.of(directory), (p -> !p.toFile().getName().endsWith(exclude)));
        Zip zip = new Zip();
        zip.packFiles(sourceList, new File(output));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
