package ru.job4j.ood.lsp.parking;

/**
 * Интерфейс, описывающий парковочное место
 */

public interface ParkingSpot {
    boolean isFree();

    void occupy(Vehicle vehicle);

    void free();

    Vehicle getVehicle();
}
