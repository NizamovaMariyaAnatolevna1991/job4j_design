package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Интерфейс, описывающий парковку
 */

public interface Parking {
    boolean park(Vehicle vehicle);

    boolean leave(String regNumber);

    List<Vehicle> getVehicles();

    int getAvailablePlaces();
}
