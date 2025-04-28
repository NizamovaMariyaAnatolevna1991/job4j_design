package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class NonCollisionMapTest {

    private final SimpleMap<Integer, String> map = new NonCollisionMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        assertThat(map).hasSize(4).contains(1, 2, 3, 4);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> iterator = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> iterator = map.iterator();
        map.get(1);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.get(null)).isNull();
    }

    @Test
    void whenPutNewKeyThenTrueAndSizeIncreases() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(1, "one")).isTrue();
        assertThat(map).hasSize(1);
    }

    @Test
    void whenPutExistingKeyThenFalseAndSizeDoesNotChange() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        assertThat(map.put(1, "newOne")).isFalse();
        assertThat(map).hasSize(1);
        assertThat(map.get(1)).isEqualTo("one");
    }

    @Test
    void whenGetExistingKeyThenReturnValue() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        assertThat(map.get(1)).isEqualTo("one");
    }

    @Test
    void whenGetNonExistingKeyThenNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        assertThat(map.get(2)).isNull();
    }

    @Test
    void whenRemoveExistingKeyThenTrueAndSizeDecreases() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        assertThat(map.remove(1)).isTrue();
        assertThat(map).hasSize(0);
        assertThat(map.get(1)).isNull();
    }

    @Test
    void whenRemoveNonExistingKeyThenFalseAndSizeDoesNotChange() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(1);
    }

    @Test
    void whenIterateThenReturnAllKeys() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Iterator<Integer> iterator = map.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            assertThat(key).isIn(1, 2, 3);
            size++;
        }
        assertThat(size).isEqualTo(3);
    }

    @Test
    void whenModifyDuringIterationThenConcurrentModificationException() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        map.put(2, "two");

        Iterator<Integer> iterator = map.iterator();
        map.put(3, "three");

        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenExceedLoadFactorThenTableExpands() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");

        assertThat(map.put(8, "eight")).isTrue();
    }

    @Test
    void whenCollisionAfterExpansionThenPutReturnsFalse() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "one");
        map.put(9, "nine");
        assertThat(map.put(17, "seventeen")).isFalse();
    }

    @Test
    void whenPutNullKeyThenTrueAndSizeIncreases() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "nullValue")).isTrue();
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isEqualTo("nullValue");
    }

    @Test
    void whenRemoveNullKeyThenTrueAndSizeDecreases() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(null, "nullValue");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(0);
        assertThat(map.get(null)).isNull();
    }
}