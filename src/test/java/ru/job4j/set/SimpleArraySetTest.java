package ru.job4j.set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenContainsExistingElementThenTrue() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        assertThat(set.contains(2)).isTrue();
    }

    @Test
    void whenContainsNonExistingElementThenFalse() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        assertThat(set.contains(5)).isFalse();
    }

    @Test
    void whenContainsNullThenTrue() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        set.add(null);
        assertThat(set.contains(null)).isTrue();
    }

    @Test
    void whenContainsNullButNotAddedThenFalse() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        assertThat(set.contains(null)).isFalse();
    }

    @Test
    void whenSetIsEmptyThenContainsReturnsFalse() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
    }
}