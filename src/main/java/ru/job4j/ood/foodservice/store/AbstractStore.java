package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class AbstractStore implements Store {

    protected List<Food> foods = new ArrayList<>();

    @Override
    public void accept(Food food) {
        if (canAccept(food)) {
            foods.add(food);
        }
    }


    /**
     * Метод возвращает все продукты в хранилище
     */

    protected double getFreshness(Food food) {
        long totalDays = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long elapsedDays = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDateTime.now());

        if (totalDays == 0) {
            return elapsedDays > 0 ? 0.0 : 1.0;
        }
        double freshness = 1.0 - ((double) elapsedDays / totalDays);
        return Math.max(0.0, Math.min(1.0, freshness));
    }

    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }

    /**
     * Очищение хранилища
     */

    public void clear() {
        foods.clear();
    }
}
