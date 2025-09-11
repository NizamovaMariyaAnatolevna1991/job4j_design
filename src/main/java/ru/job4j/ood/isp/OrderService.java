package ru.job4j.ood.isp;

/**
 * OrderService жестко связан с хранением в БД,
 * если захотим сохранять в файл или облако
 * нужно будет переписать класс
 */

public class OrderService {
    private Database database = new Database();

    public void saveOrder(Object order) {
        database.save(order);
    }
}

class Database {
    public void save(Object order) {
        System.out.println("Заказ сохранен в БД");
    }
}
