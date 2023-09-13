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
        for (Store store : storeList) {
            for (Food food : foodList) {
                store.add(food, localDate);
            }
        }
    }
}
