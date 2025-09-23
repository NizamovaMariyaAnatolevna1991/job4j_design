package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

import java.time.LocalDateTime;
import java.util.List;

public interface Store {
    void accept(Food food);

    /**
     * Метод определяет может ли хранилище принять продукт
     */

    boolean canAccept(Food food, LocalDateTime currentDate);

    List<Food> getFoods();

    void clear();
}
