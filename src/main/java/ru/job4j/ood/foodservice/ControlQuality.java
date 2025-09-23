package ru.job4j.ood.foodservice;

import ru.job4j.ood.foodservice.store.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = new ArrayList<>(stores);
    }

    public void distribute(Food food, LocalDateTime currentDate) {
        for (Store store : stores) {
            if (store.canAccept(food, currentDate)) {
                store.accept(food);
                return;
            }
        }
    }

    /**
     * Перераспределяет все продукты заново
     */
    public void resort(LocalDateTime date) {
        List<Food> allFoods = new ArrayList<>();
        for (Store store : stores) {
            allFoods.addAll(store.getFoods());
            store.clear();
        }

        for (Food food : allFoods) {
            distribute(food, date);
        }
    }
}
