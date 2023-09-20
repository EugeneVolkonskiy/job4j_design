package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {

    public static final int ADD_ROOT = 1;
    public static final int ADD_CHILD = 2;
    public static final int ACTION = 3;
    public static final int PRINT = 4;
    public static final String EXIT = "Конец работы";
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static final String MENU = """
                Введите 1, чтобы добавить элемент в корень меню.
                Введите 2, чтобы добавить элемент к родительскому элементу.
                Введите 3, чтобы вызвать действие, привязанное к пункту меню.
                Введите 4, чтобы выести меню в консоль.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuItemPrinter();
        Scanner scanner = new Scanner(System.in);
        start(menu, menuPrinter, scanner);
    }

    private static void start(Menu menu, MenuPrinter menuPrinter, Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (ADD_ROOT == userChoice) {
                System.out.println("Введите корневой элемент");
                String childName = scanner.nextLine();
                menu.add(Menu.ROOT, childName, DEFAULT_ACTION);
            } else if (ADD_CHILD == userChoice) {
                System.out.println("Введите корневой элемент");
                String parentName = scanner.nextLine();
                System.out.println("Введите дочерний элемент");
                String childName = scanner.nextLine();
                menu.add(parentName, childName, DEFAULT_ACTION);
            } else if (ACTION == userChoice) {
                System.out.println("Введите элемент");
                String name = scanner.nextLine();
                Optional<Menu.MenuItemInfo> select = menu.select(name);
                select.ifPresent(menuItemInfo -> menuItemInfo.getActionDelegate().delegate());
            } else if (PRINT == userChoice) {
                menuPrinter.print(menu);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
