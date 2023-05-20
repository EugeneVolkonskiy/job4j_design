package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> botAnswer = readPhrases();
        while (run) {
            String inputPhrase = scanner.next();
            log.add(inputPhrase);
            if (OUT.equals(inputPhrase)) {
                saveLog(log);
                break;
            }
            if (STOP.equals(inputPhrase)) {
                while (!CONTINUE.equals(inputPhrase)) {
                    inputPhrase = scanner.next();
                    log.add(inputPhrase);
                    if (OUT.equals(inputPhrase)) {
                        run = false;
                        saveLog(log);
                        break;
                    }
                }
                continue;
            }
            Collections.shuffle(botAnswer);
            System.out.println(botAnswer.get(1));
            log.add(botAnswer.get(1));
        }
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(botAnswers))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/dialog.txt", "data/bot_answers.txt");
        cc.run();
    }
}
