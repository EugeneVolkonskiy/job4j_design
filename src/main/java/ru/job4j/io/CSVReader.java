package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String[] splitFilter = argsName.get("filter").split(",");
        Scanner sc = new Scanner(new FileInputStream(argsName.get("path")));
        String line = sc.nextLine();
        sc.close();
        String[] splitFirstLine = line.split(argsName.get("delimiter"));
        List<Integer> index = new ArrayList<>();
        for (String value : splitFilter) {
            for (int j = 0; j < splitFirstLine.length; j++) {
                if (value.equals(splitFirstLine[j])) {
                    index.add(j);
                }
            }
        }
        Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
            String[] splitLine = s.split(argsName.get("delimiter"));
            for (Integer in : index) {
                for (int i = 0; i < splitLine.length; i++) {
                    if (i == in) {
                        joiner.add(splitLine[i]);
                    }
                }
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(joiner);
            } else if (argsName.get("out").endsWith(".csv")) {
                try (PrintStream printStream = new PrintStream(new FileOutputStream(argsName.get("out"), true))) {
                    printStream.println(joiner);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("The number of parameters must be 4");
        }
        if (!Files.exists(Path.of(args[0].replace("-path=", "")))) {
            throw new IllegalArgumentException(String.format("Input file not exists %s", args[0]));
        }
        if (!(args[0].endsWith(".csv"))) {
            throw new IllegalArgumentException("Input file extension must be '.csv'");
        }
        if (!(args[2].endsWith(".csv") || "stdout".equals(args[2]))) {
            throw new IllegalArgumentException("Output file extension must be '.csv' or 'stdout'");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
