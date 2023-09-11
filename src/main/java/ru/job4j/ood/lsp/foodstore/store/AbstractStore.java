package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

abstract class AbstractStore implements Store {

    private final List<Food> list = new ArrayList<>();

    protected static final double WAREHOUSE_PERCENT = 25;
    protected static final double SHOP_PERCENT = 75;
    protected static final double TRASH_PERCENT = 100;

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public void remove(Food food) {
        list.remove(food);
    }

    @Override
    public void showFood() {
        list.forEach(System.out::println);
    }

    public boolean isExpired(Food food, Predicate<Double> predicate) {
        return predicate.test(food.getExpiryPercent());
    }

    @Override
    public List<Food> getList() {
        return list;
    }
}
