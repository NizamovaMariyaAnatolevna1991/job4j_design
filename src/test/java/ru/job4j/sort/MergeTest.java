package ru.job4j.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenArrayIsEmpty() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }

    @Test
    void whenArrayHasOddLengthThenCorrectlySorted() {
        int[] array = {6, 2, 9, 1, 4};
        assertThat(Merge.mergesort(array)).containsExactly(1, 2, 4, 6, 9);
    }

    @Test
    void whenArrayLengthIsOneAndTwoThenMergeWorks() {
        int[] array = {3, 1, 4};
        assertThat(Merge.mergesort(array)).containsExactly(1, 3, 4);
    }
}