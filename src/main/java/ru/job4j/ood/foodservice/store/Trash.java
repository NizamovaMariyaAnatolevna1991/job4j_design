package ru.job4j.ood.foodservice.store;

import ru.job4j.ood.foodservice.Food;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Trash extends AbstractStore {
    @Override
    public boolean canAccept(Food food) {
        return food.getExpiryDate().isBefore(LocalDateTime.now());
    }
}
