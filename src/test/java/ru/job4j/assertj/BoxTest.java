package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .contains("h")
                .startsWith("S");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(3, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .doesNotContain("Cube")
                .endsWith("object");
    }

    @Test
    void whenVertexEqualsEight() {
        Box box = new Box(8, 8);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8)
                .isPositive()
                .isEven();
    }

    @Test
    void whenVertexEqualsZero() {
        Box box = new Box(0, 15);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(0)
                .isLessThan(4)
                .isZero();
    }

    @Test
    void whenExist() {
        Box box = new Box(4, 12);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isEqualTo(true);
    }

    @Test
    void whenNotExist() {
        Box box = new Box(-2, 12);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isEqualTo(false);
    }

    @Test
    void whenVertex4ThenArea62dot35() {
        Box box = new Box(4, 6);
        double result = box.getArea();
        assertThat(result).isEqualTo(62.35d,withPrecision(0.05d))
                .isGreaterThan(51.5d);
    }

    @Test
    void whenVertex5ThenArea0() {
        Box box = new Box(5, 6);
        double result = box.getArea();
        assertThat(result).isEqualTo(0.0d,withPrecision(0.01d))
                .isLessThan(1d);
    }
}