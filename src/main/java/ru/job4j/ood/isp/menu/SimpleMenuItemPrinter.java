package ru.job4j.ood.isp.menu;

public class SimpleMenuItemPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String number = menuItemInfo.getNumber();
            String name = menuItemInfo.getName();
            System.out.println(number + name);
        }
    }
}
