package ru.job4j.ood.foodservice;

import ru.job4j.ood.foodservice.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = new ArrayList<>(stores);
    }

    public void distribute(Food food) {
        for (Store store : stores) {
            if (store.canAccept(food)) {
                store.accept(food);
                return;
            }
        }
    }
}
