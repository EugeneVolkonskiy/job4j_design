package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractStore implements Store {

    private final List<Food> list = new ArrayList<>();

    protected LocalDate localDate;

    protected ExpiryPercentCalculator calculator = new ExpiryPercentCalculator();

    protected static final double WAREHOUSE_PERCENT = 25;
    protected static final double SHOP_PERCENT = 75;
    protected static final double TRASH_PERCENT = 100;

    @Override
    public void add(Food food, LocalDate localDate) {
        list.add(food);
    }

    public List<Food> getList() {
        return list;
    }
}
