package ru.job4j.ood.lsp.parking;

public interface ParkingSpot {
    boolean isFree();

    void occupy(Vehicle vehicle);

    void free();

    Vehicle getVehicle();
}
