package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Apple;
import ru.job4j.ood.lsp.food.Bread;
import ru.job4j.ood.lsp.food.Chocolate;
import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.store.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenExpiryPercentLess25ThanStoreInWarehouse() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash());
        Food bread = new Bread("Bread", LocalDate.of(2023, 9, 12),
                LocalDate.of(2023, 9, 6), 40, 10);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.sort(bread);
        Food result = storeList.get(0).getList().get(0);
        assertThat(result).isEqualTo(bread);
    }

    @Test
    public void whenExpiryPercentMore75ThanStoreInShop() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash());
        Food chocolate = new Chocolate("Chocolate", LocalDate.of(2023, 9, 10),
                LocalDate.of(2023, 8, 1), 100, 20);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.sort(chocolate);
        Food result = storeList.get(1).getList().get(0);
        assertThat(result).isEqualTo(chocolate);
    }

    @Test
    public void whenExpiryPercentMore100ThanStoreInTrash() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash());
        Food apple = new Apple("Apple", LocalDate.of(2023, 9, 6),
                LocalDate.of(2023, 9, 1), 90, 30);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.sort(apple);
        Food result = storeList.get(2).getList().get(0);
        assertThat(result).isEqualTo(apple);
    }
}