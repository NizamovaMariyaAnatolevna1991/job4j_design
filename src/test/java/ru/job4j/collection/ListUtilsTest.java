package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, x -> x % 2 != 0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, x -> x % 2 != 0, 5);
        assertThat(input).containsExactly(5, 5);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> elements = Arrays.asList(1, 6);
        ListUtils.removeAll(input, elements);
        assertThat(input).containsExactly(3);
    }
}