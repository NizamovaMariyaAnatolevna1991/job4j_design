package ru.job4j.ood.isp;

/**
 * Животное Dog вынуждено реализовывать все методы Animal
 * но ему ни к чему метод fly
 */

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Собака ест");
    }

    @Override
    public void walk() {
        System.out.println("Собака гуляет");
    }

    @Override
    public void swim() {
        System.out.println("Собака гуляет");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException("Собаки не летают!");
    }
}
