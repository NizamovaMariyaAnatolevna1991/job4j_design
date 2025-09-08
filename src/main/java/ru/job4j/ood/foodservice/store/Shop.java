package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean canAccept(Food food) {
        double freshness = getFreshness(food);
        boolean inRange = freshness > 0.25 && freshness <= 0.75;

        if (inRange && freshness < 0.5) {
            food.setDiscount(0.2);
        }

        return inRange;
    }
}
