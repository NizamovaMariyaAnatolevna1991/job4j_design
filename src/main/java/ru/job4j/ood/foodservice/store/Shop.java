package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

import java.time.LocalDateTime;

public class Shop extends AbstractStore {
    @Override
    public boolean canAccept(Food food, LocalDateTime currentDate) {
        double freshness = getFreshness(food,currentDate);
        boolean inRange = freshness >= MIN_FRESHNESS_FOR_SALE && freshness <= FRESH_THRESHOLD;

        if (inRange && freshness < DISCOUNT_THRESHOLD) {
            food.setDiscount(DISCOUNT);
        }

        return inRange;
    }
}
