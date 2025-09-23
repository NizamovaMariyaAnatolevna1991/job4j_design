package ru.job4j.ood.lsp.parking;

public class Car implements Vehicle {
    private final String regNumber;
    private final int size = 1;

    public Car(String regNumber) {

        this.regNumber = regNumber;
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
        Car car = (Car) o;
        return regNumber.equals(car.regNumber);
    }

    @Override
    public int hashCode() {
        return regNumber.hashCode();
    }
}
