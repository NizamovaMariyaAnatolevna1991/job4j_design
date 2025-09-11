package ru.job4j.ood.isp;

/**
 * June вынужден реализовывать методы, которые ему еще не
 * разрешено/ не умеет/ не основная работа
 */

public class JuniorDeveloper implements Employee {
    @Override
    public void writeCode() {
        System.out.println("Пишу код...");
    }

    @Override
    public void conductInterview() {
        throw new UnsupportedOperationException("Я ещё не готов");
    }

    @Override
    public void approveBudget() {
        throw new UnsupportedOperationException("Не мой уровень");
    }

    @Override
    public void fixPrinter() {
        System.out.println("Ок, я починю принтер... опять");
    }
}
