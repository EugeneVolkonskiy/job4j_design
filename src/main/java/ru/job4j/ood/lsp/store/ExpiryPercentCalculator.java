package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ExpiryPercentCalculator {

    private final List<Food> foodList;
    private final LocalDate localDate;

    public ExpiryPercentCalculator(List<Food> foodList, LocalDate localDate) {
        this.foodList = foodList;
        this.localDate = localDate;
    }

    public List<Food> updateExpiryPercent() {
        List<Food> foods = new ArrayList<>();
        for (Food food : foodList) {
            calculateExpiryPercent(food, localDate);
            foods.add(food);
        }
        return foods;
    }

    private void calculateExpiryPercent(Food food, LocalDate localDate) {
        long periodCreateExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long periodCreateNow = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
        food.setExpiryPercent(((double) periodCreateNow / periodCreateExpired) * 100);
    }
}
