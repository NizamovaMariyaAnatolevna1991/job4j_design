package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    SimpleConvert simpleConvert = new SimpleConvert();

    @Test
    void checkArray() {
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(1).isNotNull().isEqualTo("second");
        assertThat(list).last().isNotNull().isEqualTo("five");
    }

    @Test
    void checkToSet() {
        Set<String> set = simpleConvert.toSet("first", "second", "first", "four", "five");
        assertThat(set).containsExactlyInAnyOrder("first", "second", "four", "five")
                .hasSize(4);
    }

    @Test
    void checkToMap() {
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "first", "four", "five");
        assertThat(map).hasSize(4)
                .containsKeys("first", "second", "four", "five")
                .containsValues(0, 1, 3, 4)
                .doesNotContainKey("eight")
                .doesNotContainValue(5)
                .containsEntry("first", 0);
    }
}
















