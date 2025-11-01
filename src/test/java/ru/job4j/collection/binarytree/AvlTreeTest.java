package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void whenInsertToEmptyTreeThenListContainsOneElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(1)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly(1);
    }

    @Test
    void whenInsertTwoToEmptyTreeThenListContainsTwoElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(1)).isTrue();
        assertThat(tree.insert(2)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly(1, 2);
    }

    @Test
    void whenInsertElementThenContainElementOk() {
        AvlTree<String> tree = new AvlTree<>();
        tree.insert("first");
        tree.insert("second");
        tree.insert("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenRemoveNodeHasRightAndLeftThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{10, 6, 14, 4, 8, 12, 16, 9, 13, 15, 3, 5, 7}) {
            tree.insert(element);
        }
        tree.remove(4);
        assertThat(tree.contains(4)).isFalse();
        assertThat(tree.minimum()).isEqualTo(3);
    }

    @Test
    void whenRemoveNodeHasLeftThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 9, 13, 15, 3, 5, 7}) {
            tree.insert(element);
        }
        tree.remove(16);
        assertThat(tree.contains(16)).isFalse();
        assertThat(tree.maximum()).isEqualTo(15);
    }

    @Test
    void whenRemoveNodeHasRightThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 9, 13, 15, 3, 5, 7}) {
            tree.insert(element);
        }
        tree.remove(12);
        assertThat(tree.contains(12)).isFalse();
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }
}