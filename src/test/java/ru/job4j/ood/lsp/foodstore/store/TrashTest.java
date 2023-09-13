package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.food.Chocolate;
import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    public void whenAddFoodInTrashThanFoodAdded() {
        Store trash = new Trash();
        Food food = new Chocolate("Chocolate", LocalDate.of(2023, 9, 5),
                LocalDate.of(2023, 7, 1), 100, 20);
        trash.add(food, LocalDate.of(2023, 9, 8));
        assertThat(trash.getList().get(0)).isEqualTo(food);
    }
}