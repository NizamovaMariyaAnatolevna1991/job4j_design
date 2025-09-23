package ru.job4j.ood.foodservice;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodservice.store.Shop;
import ru.job4j.ood.foodservice.store.Store;
import ru.job4j.ood.foodservice.store.Trash;
import ru.job4j.ood.foodservice.store.Warehouse;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    private static final double DISCOUNT = 0.2;

    @Test
    void whenDistributeThenCorrectStorage() {
        LocalDateTime now = LocalDateTime.now();

        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        List<Store> stores = List.of(warehouse, shop, trash);
        ControlQuality control = new ControlQuality(stores);

        Food fresh = new Food("Milk", now.minusDays(1), now.plusDays(30), 100.0);

        control.distribute(fresh, now);

        List<Food> foods = warehouse.getFoods();
        System.out.println("В хранилище: " + foods);

        assertThat(foods).contains(fresh);
    }

    @Test
    void whenDistributeThenCorrectStorageStore() {
        LocalDateTime now = LocalDateTime.now();
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality control = new ControlQuality(stores);

        Food fresh = new Food("Milk", now.minusDays(1), now.plusDays(30), 100.0);
        control.distribute(fresh, now);
        assertThat(warehouse.getFoods()).contains(fresh);

        Food almost = new Food("Yogurt", now.minusDays(15), now.plusDays(5), 50.0);
        control.distribute(almost, now);
        assertThat(shop.getFoods()).contains(almost);
        assertThat(almost.getDiscount()).isEqualTo(0.2);

        Food expired = new Food("Cheese", now.minusDays(10), now.minusDays(1), 30.0);
        control.distribute(expired, now);
        assertThat(trash.getFoods()).contains(expired);
    }

    @Test
    void resort() {
        LocalDateTime now = LocalDateTime.now();

        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        List<Store> stores = List.of(warehouse, shop, trash);

        ControlQuality control = new ControlQuality(stores);

        Food milk = new Food("Milk", now.minusDays(1), now.plusDays(15), 100.0);

        control.distribute(milk, now);
        assertThat(warehouse.getFoods()).contains(milk);
        assertThat(shop.getFoods()).doesNotContain(milk);

        LocalDateTime future = now.plusDays(90);

        control.resort(future);

        assertThat(warehouse.getFoods()).doesNotContain(milk);
        assertThat(shop.getFoods()).doesNotContain(milk);
        assertThat(trash.getFoods()).contains(milk);
    }
}