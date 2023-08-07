package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    public static final int ADD_FILE = 1;
    public static final int SHOW_FILE = 2;

    public static final String SELECT = "Выберите меню";
    public static final String SELECT_DIRECTORY = "Укажите директорию";
    public static final String SELECT_FILE = "Укажите имя файла";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, чтобы указать название файла, который надо загрузить.
                Введите 2, чтобы показать содержимое файла.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        System.out.println(SELECT_DIRECTORY);
        Scanner scanner = new Scanner(System.in);
        String directory = scanner.nextLine();
        AbstractCache<String, String> abstractCache = new DirFileCache(directory);
        start(abstractCache, scanner);
    }

    private static void start(AbstractCache<String, String> abstractCache, Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (ADD_FILE == userChoice) {
                abstractCache.get(selectFile(scanner));
            } else if (SHOW_FILE == userChoice) {
                System.out.println(abstractCache.get(selectFile(scanner)));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static String selectFile(Scanner scanner) {
        System.out.println(SELECT_FILE);
        return scanner.nextLine();
    }
}

