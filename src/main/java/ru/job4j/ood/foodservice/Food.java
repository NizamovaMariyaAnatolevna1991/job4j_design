package ru.job4j.ood.foodservice;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Food {

    private String name;
    private LocalDateTime expiryDate;
    private LocalDateTime createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name) &&
                Objects.equals(createDate, food.createDate) &&
                Objects.equals(expiryDate, food.expiryDate) &&
                Double.compare(price, food.price) == 0 &&
                Double.compare(discount, food.discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
                ", expiryDate=" + expiryDate +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
