package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

abstract class AbstractStore implements Store {

    private final List<Food> list = new ArrayList<>();

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

    public double expiryPercent(Food food) {
        long periodCreateExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long periodCreateNow = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDateTime.now());
        return ((double) periodCreateNow / periodCreateExpired) * 100;
    }

    public boolean isExpired(Food food, Predicate<Double> predicate) {
        return predicate.test(expiryPercent(food));
    }

    @Override
    public List<Food> getList() {
        return list;
    }
}
