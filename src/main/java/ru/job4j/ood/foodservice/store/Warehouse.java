package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

public class Warehouse extends AbstractStore {
    @Override
    public boolean canAccept(Food food) {
        return getFreshness(food) > 0.75;
    }
}
