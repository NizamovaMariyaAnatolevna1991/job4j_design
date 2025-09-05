package ru.job4j.ood.lsp;

/**
 * Когда используем Duck duck = new ElectricDuck() ожидаем, что
 * duck.quack() работает, но если утка не включена выбрасывается исключение
 * в противном случае на бибикает
 */
public class ElectricDuck extends Duck {
    private boolean pluggedIn = false;

    public void plugIn() {
        pluggedIn = true;
    }

    @Override
    public void quack() {
        if (!pluggedIn) {
            throw new IllegalStateException("Утка не подключена к розетке!");
        }

        System.out.println("Бип-бип");
    }
}
