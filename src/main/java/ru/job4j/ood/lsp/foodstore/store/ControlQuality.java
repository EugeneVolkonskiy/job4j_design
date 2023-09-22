package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private final StoreService storeService;

    public ControlQuality(StoreService storeService) {
        this.storeService = storeService;
    }

    public void sort(List<Food> foodList, LocalDate localDate) {
        for (Store store : storeService.getStoreList()) {
            for (Food food : foodList) {
                store.add(food, localDate);
            }
        }
    }

    public void resort(LocalDate localDate) {
        List<Food> storeFood = storeService.getStoreFood();
        storeService.clearStore();
        sort(storeFood, localDate);
    }
}

