package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void sort(List<Food> foodList, LocalDate localDate) {
        ExpiryPercentCalculator expiryPercentCalculator = new ExpiryPercentCalculator(foodList, localDate);
        foodList = expiryPercentCalculator.updateExpiryPercent();
        for (Store store : storeList) {
            for (Food food : foodList) {
                if (store.checkExpiry(food)) {
                    store.add(food);
                }
            }
        }
    }
}
