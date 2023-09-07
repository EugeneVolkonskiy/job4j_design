package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;
import java.util.List;

public class ControlQuality {

    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void sort(Food food) {
        for (Store store : storeList) {
            if (store.checkExpiry(food)) {
                store.add(food);
                System.out.println(food.getName() + " stored in " + store.getClass());
            }
        }
    }
}
