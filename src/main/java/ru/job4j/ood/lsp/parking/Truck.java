package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truck implements Vehicle {
    private final String regNumber;
    private final int size;

    public Truck(String regNumber, int size) {
        this.regNumber = regNumber;
        this.size = size;
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public String getRegNumber() {

        return regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return regNumber.equals(truck.regNumber);
    }

    @Override
    public int hashCode() {

        return regNumber.hashCode();
    }
}