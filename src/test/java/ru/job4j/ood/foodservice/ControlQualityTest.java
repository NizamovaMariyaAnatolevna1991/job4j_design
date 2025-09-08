package ru.job4j.ood.foodservice;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodservice.store.Shop;
import ru.job4j.ood.foodservice.store.Store;
import ru.job4j.ood.foodservice.store.Trash;
import ru.job4j.ood.foodservice.store.Warehouse;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenDistributeThenCorrectStorage() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        List<Store> stores = List.of(warehouse, shop, trash);
        ControlQuality control = new ControlQuality(stores);

        Food fresh = new Food(
                "Milk",
                LocalDateTime.of(2025, 9, 7, 10, 0),
                LocalDateTime.of(2025, 9, 15, 10, 0),
                100.0
        );

        control.distribute(fresh);

        List<Food> foods = warehouse.getFoods();
        System.out.println("В хранилище: " + foods);

        assertThat(foods).contains(fresh);
    }

    @Test
    void whenDistributeThenCorrectStorageStore() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality control = new ControlQuality(stores);

        Food fresh = new Food(
                "Milk",
                LocalDateTime.of(2025, 9, 8, 10, 0),
                LocalDateTime.of(2025, 9, 15, 10, 0),
                100.0
        );
        control.distribute(fresh);
        assertThat(warehouse.getFoods()).contains(fresh);

        Food almost = new Food(
                "Yogurt",
                LocalDateTime.of(2025, 9, 1, 10, 0),
                LocalDateTime.of(2025, 9, 12, 10, 0),
                50.0
        );
        control.distribute(almost);
        assertThat(shop.getFoods()).contains(almost);
        assertThat(almost.getDiscount()).isEqualTo(0.2);

        Food expired = new Food(
                "Cheese",
                LocalDateTime.of(2025, 9, 1, 10, 0),
                LocalDateTime.of(2025, 9, 5, 10, 0),
                0.0
        );
        control.distribute(expired);
        assertThat(trash.getFoods()).contains(expired);
    }
}