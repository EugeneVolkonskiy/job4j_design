package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;

public class Shop extends AbstractStore {

    @Override
    public void add(Food food, LocalDate localDate) {
        calculator.calculateExpiryPercent(food, localDate);
        if (calculator.isExpired(food, ex -> ex > WAREHOUSE_PERCENT && ex <= SHOP_PERCENT)) {
            getList().add(food);
        } else if (calculator.isExpired(food, ex -> ex > SHOP_PERCENT && ex <= TRASH_PERCENT)) {
            food.setPrice(food.getPrice() * ((100 - food.getDiscount())) / 100);
            getList().add(food);
        }
    }
}