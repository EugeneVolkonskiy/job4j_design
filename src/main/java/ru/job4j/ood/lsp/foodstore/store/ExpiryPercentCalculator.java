package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public class ExpiryPercentCalculator {

    public void calculateExpiryPercent(Food food, LocalDate localDate) {
        long periodCreateExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long periodCreateNow = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
        food.setExpiryPercent(((double) periodCreateNow / periodCreateExpired) * 100);
    }

    public boolean isExpired(Food food, Predicate<Double> predicate) {
        return predicate.test(food.getExpiryPercent());
    }
}
