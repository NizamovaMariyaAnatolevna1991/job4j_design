package ru.job4j.ood.lsp;

/**
 * Используя Emloyee emp = new Manager(),
 * вызывая emp.work() будет работать, но
 * выбрасывается исключение
 */

public class Manager extends Employee {

    @Override
    public void work() {
        throw new UnsupportedOperationException("Менеджер не пишет код");
    }
}
