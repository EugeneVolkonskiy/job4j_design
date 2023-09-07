package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.util.List;

public interface Store {

    void add(Food food);

    void remove(Food food);

    void showFood();

    boolean checkExpiry(Food food);

    List<Food> getList();
}