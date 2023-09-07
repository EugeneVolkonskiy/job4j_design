package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

public class Shop extends AbstractStore {

    @Override
    public boolean checkExpiry(Food food) {
        boolean rsl = false;
        if (isExpired(food, ex -> ex > 25 && ex <= 75)) {
            rsl = true;
        } else if (isExpired(food, ex -> ex > 75 && ex <= 100)) {
            food.setPrice(food.getPrice() * ((100 - food.getDiscount())) / 100);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void add(Food food) {
        if (checkExpiry(food)) {
            getList().add(food);
        } else {
            System.out.printf("Storing in Shop failed, wrong expiry percent: %f%n", expiryPercent(food));
        }
    }
}