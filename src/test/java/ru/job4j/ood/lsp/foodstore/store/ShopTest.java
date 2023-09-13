package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.food.Apple;
import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void whenAddFoodInShopThanFoodAdded() {
        Store shop = new Shop();
        Food food = new Apple("Apple", LocalDate.of(2023, 9, 12),
                LocalDate.of(2023, 8, 1), 40, 10);
        shop.add(food, LocalDate.of(2023, 9, 8));
        assertThat(shop.getList().get(0)).isEqualTo(food);
    }
}

