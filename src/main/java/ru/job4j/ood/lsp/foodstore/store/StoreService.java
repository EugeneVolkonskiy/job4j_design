package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.util.List;

public interface StoreService {

    List<Store> getStoreList();

    List<Food> getStoreFood();

    void clearStore();

}
