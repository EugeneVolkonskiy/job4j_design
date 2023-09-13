package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.food.Bread;
import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenAddFoodInWarehouseThanFoodAdded() {
        Store warehouse = new Warehouse();
        Food food = new Bread("Bread", LocalDate.of(2023, 9, 12),
                LocalDate.of(2023, 9, 7), 40, 10);
        warehouse.add(food, LocalDate.of(2023, 9, 8));
        assertThat(warehouse.getList().get(0)).isEqualTo(food);
    }
}