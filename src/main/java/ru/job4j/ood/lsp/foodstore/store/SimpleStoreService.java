package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleStoreService implements StoreService {

    private List<Store> storeList;

    public SimpleStoreService(List<Store> storeList) {
        this.storeList = storeList;
    }

    @Override
    public List<Store> getStoreList() {
        return storeList;
    }

    @Override
    public List<Food> getStoreFood() {
        return storeList.stream()
                .flatMap(store -> store.getList().stream())
                .peek(food -> food.setPrice(food.getPrimordialPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void clearStore() {
        storeList.forEach(store -> store.getList().clear());
    }
}
