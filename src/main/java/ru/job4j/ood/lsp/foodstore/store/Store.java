package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.util.List;

public interface Store {

    void add(Food food, LocalDate localDate);

    List<Food> getList();
}
