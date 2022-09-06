package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyNames() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotContainsEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1 = A", "2B"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2B");
    }

    @Test
    void whenStartsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1 = A", "=2B"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=2B");
    }

    @Test
    void whenOnlyEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1 = A", "="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=");
    }
}