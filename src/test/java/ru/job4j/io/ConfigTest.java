package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenLineHasNoKeyThenThrowException() {
        String path = "./data/invalid_format_no_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid format: key is missing");
    }

    @Test
    void whenLineHasNoValueThenThrowException() {
        String path = "./data/invalid_format_no_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid format: value is missing");
    }

    @Test
    void whenLineHasNoEqualsSignThenThrowException() {
        String path = "./data/invalid_format_no_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid format: line does not contain '='");
    }

    @Test
    void whenValidFormatThenLoadCorrectly() throws IOException {
        String path = "./data/valid_format.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
        assertThat(config.value("key2")).isEqualTo("value=2");
    }
}