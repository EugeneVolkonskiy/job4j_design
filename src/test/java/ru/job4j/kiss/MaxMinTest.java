package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenMaxInteger() {
        List<Integer> list = List.of(9, 2, 8);
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.max(list, Comparator.comparingInt(o -> o));
        assertThat(rsl).isEqualTo(9);
    }

    @Test
    void whenMinInteger() {
        List<Integer> list = List.of(7, 5, 3);
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.min(list, Comparator.comparingInt(o -> o));
        assertThat(rsl).isEqualTo(3);
    }

    @Test
    void whenMaxString() {
        List<String> list = List.of("a", "aaa", "aa");
        MaxMin maxMin = new MaxMin();
        String rsl = maxMin.max(list, Comparator.comparingInt(String::length));
        assertThat(rsl).isEqualTo("aaa");
    }

    @Test
    void whenMinString() {
        List<String> list = List.of("a", "aaa", "aa");
        MaxMin maxMin = new MaxMin();
        String rsl = maxMin.min(list, Comparator.comparingInt(String::length));
        assertThat(rsl).isEqualTo("a");
    }
}