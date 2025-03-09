package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void numberOfVerticesIsNull() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0);
    }

    @Test
    void numberOfVerticesIsEight() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8);
    }

    @Test
    void isExist() {
        Box box = new Box(8, 10);
        boolean number = box.isExist();
        assertThat(number).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(5, 10);
        boolean number = box.isExist();
        assertThat(number).isFalse();
    }

    @Test
    void getAreaOfSphere() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256, withPrecision(1.04d));
    }

    @Test
    void getAreaOfCube() {
        Box box = new Box(8, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(96);
    }
}