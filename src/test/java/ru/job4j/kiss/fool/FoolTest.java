package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FoolTest {

    @Test
    void whenNumberDivisibleBy3And5ThenFizzBuzz() {
        assertEquals("FizzBuzz", Fool.getExpectedAnswer(15));
        assertEquals("FizzBuzz", Fool.getExpectedAnswer(30));
        assertEquals("FizzBuzz", Fool.getExpectedAnswer(45));
    }

    @Test
    void whenNumberDivisibleBy3OnlyThenFizz() {
        assertEquals("Fizz", Fool.getExpectedAnswer(3));
        assertEquals("Fizz", Fool.getExpectedAnswer(6));
        assertEquals("Fizz", Fool.getExpectedAnswer(9));
        assertEquals("Fizz", Fool.getExpectedAnswer(12));
    }

    @Test
    void whenNumberDivisibleBy5OnlyThenBuzz() {
        assertEquals("Buzz", Fool.getExpectedAnswer(5));
        assertEquals("Buzz", Fool.getExpectedAnswer(10));
        assertEquals("Buzz", Fool.getExpectedAnswer(20));
    }

    @Test
    void whenNumberNotDivisibleBy3Or5ThenItself() {
        assertEquals("1", Fool.getExpectedAnswer(1));
        assertEquals("2", Fool.getExpectedAnswer(2));
        assertEquals("4", Fool.getExpectedAnswer(4));
        assertEquals("7", Fool.getExpectedAnswer(7));
        assertEquals("11", Fool.getExpectedAnswer(11));
    }

    @Test
    void whenNumberIs1ThenReturn1() {
        assertEquals("1", Fool.getExpectedAnswer(1));
    }

    @Test
    void whenNumberIs100ThenBuzz() {
        assertEquals("Buzz", Fool.getExpectedAnswer(100));
    }
}