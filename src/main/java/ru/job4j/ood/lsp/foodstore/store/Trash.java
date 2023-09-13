package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;

public class Trash extends AbstractStore {

    @Override
    public void add(Food food, LocalDate localDate) {
        calculator.calculateExpiryPercent(food, localDate);
        if (calculator.isExpired(food, ex -> ex > TRASH_PERCENT)) {
            getList().add(food);
        }
    }
}
