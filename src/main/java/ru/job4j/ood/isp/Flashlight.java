package ru.job4j.ood.isp;

/**
 * Фонарик жестко привязан к одной конкретной батарейке
 * Если мы захотим фонарик с аккумулятором, придется переписать весь класс
 */

public class Flashlight {
    private Battery battery = new Battery();

    public void turnOn() {
        if (battery.hasPower()) {
            System.out.println("Фонарик светит!");
        } else {
            System.out.println("Батарейка села...");
        }
    }
}

class Battery {
    private int charge = 100;

    public boolean hasPower() {
        return charge > 0;
    }

    public void use() {
        charge -= 10;
    }
}
