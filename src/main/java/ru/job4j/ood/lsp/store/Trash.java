package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

public class Trash extends AbstractStore {

    @Override
    public boolean checkExpiry(Food food) {
        return isExpired(food, ex -> ex > TRASH_PERCENT);
    }

    @Override
    public void add(Food food) {
        if (checkExpiry(food)) {
            getList().add(food);
        } else {
            System.out.printf("Storing in Trash failed, wrong expiry percent: %f%n", food.getExpiryPercent());
        }
    }
}
