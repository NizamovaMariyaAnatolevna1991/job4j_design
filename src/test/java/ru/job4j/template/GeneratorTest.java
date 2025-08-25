package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("Тесты отключены.")
class GeneratorTest {

    @Test
    void whenStringSuccess() {
        Generator generator = new GeneratorStr();
        String template = "I am a ${name}, Who are ${subject}? ";
        String expected = "I am a Vasia, Who are you? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Vasia");
        args.put("subject", "you");
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenTemplateHasMissingKeyThenThrowException() {
        Generator generator = new GeneratorStr();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Vasia");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("subject")
                .hasMessageContaining("not found in arguments");
    }

    @Test
    void whenArgsHasExtraKeyThenThrowException() {
        Generator generator = new GeneratorStr();

        String template = "I am a ${name}, Who are ${subject}? ";

        Map<String, String> args = new HashMap<>();
        args.put("name", "Vasia");
        args.put("subject", "you");
        args.put("extra", "value");

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("extra")
                .hasMessageContaining("not used in template");
    }

    @Test
    void whenTemplateIsEmptyThenSuccessIfArgsIsEmpty() {
        Generator generator = new GeneratorStr();

        String result = generator.produce("", Map.of());

        assertThat(result).isEmpty();
    }

    @Test
    void whenTemplateIsEmptyButArgsNotEmptyThenThrow() {
        Generator generator = new GeneratorStr();

        Map<String, String> args = Map.of("name", "Petr");

        assertThatThrownBy(() -> generator.produce("", args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateIsNullThenThrow() {
        Generator generator = new GeneratorStr();

        assertThatThrownBy(() -> generator.produce(null, Map.of()))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void whenArgsIsNullThenThrow() {
        Generator generator = new GeneratorStr();

        assertThatThrownBy(() -> generator.produce("Hello ${name}", null))
                .isInstanceOf(NullPointerException.class);
    }
}