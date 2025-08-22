package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 0;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            startAt++;

            String expected = getExpectedAnswer(startAt);

            var answer = input.nextLine();

            if (!answer.equals(getExpectedAnswer(startAt))) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
        }

        System.out.println("Поздравляем! Ты дошел до 100!");
    }

    private static String getExpectedAnswer(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }
}
