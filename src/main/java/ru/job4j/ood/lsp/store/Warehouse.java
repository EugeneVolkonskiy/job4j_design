package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

public class Warehouse extends AbstractStore {

    @Override
    public boolean checkExpiry(Food food) {
        return isExpired(food, ex -> ex <= 25);
    }

    @Override
    public void add(Food food) {
        if (checkExpiry(food)) {
            getList().add(food);
        } else {
            System.out.printf("Storing in Warehouse failed, wrong expiry percent: %f%n", expiryPercent(food));
        }
    }
}
