package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

import java.time.LocalDateTime;

public class Warehouse extends AbstractStore {
    @Override
    public boolean canAccept(Food food, LocalDateTime currentDate) {
        return getFreshness(food, currentDate) > FRESH_THRESHOLD;
    }
}
