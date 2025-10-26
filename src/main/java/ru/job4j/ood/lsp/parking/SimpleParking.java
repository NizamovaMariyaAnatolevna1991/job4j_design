package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private final List<ParkingSpot> carSpots = new ArrayList<>();
    private final List<ParkingSpot> truckSpots = new ArrayList<>();

    public SimpleParking(int carSpotsCount, int truckSpotsCount) {
        for (int i = 0; i < carSpotsCount; i++) {
            carSpots.add(new SimpleParkingSpot());
        }
        for (int i = 0; i < truckSpotsCount; i++) {
            truckSpots.add(new SimpleParkingSpot());
        }
    }

    @Override
    public boolean park(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            for (ParkingSpot spot : carSpots) {
                if (spot.isFree()) {
                    spot.occupy(vehicle);
                    return true;
                }
            }
        } else {
            if (tryParkOnTruckSpot(vehicle)) {
                return true;
            }
            if (tryParkOnCarSpots(vehicle)) {
                return true;
            }
        }
        return false;
    }

    private boolean tryParkOnTruckSpot(Vehicle vehicle) {
        for (ParkingSpot spot : truckSpots) {
            if (spot.isFree()) {
                spot.occupy(vehicle);
                return true;
            }
        }
        return false;
    }

    private boolean tryParkOnCarSpots(Vehicle vehicle) {
        int needed = vehicle.getSize();
        List<ParkingSpot> spots = carSpots;

        for (int i = 0; i <= spots.size() - needed; i++) {
            boolean canFit = true;
            for (int j = i; j < i + needed; j++) {
                if (!spots.get(j).isFree()) {
                    canFit = false;
                    break;
                }
            }

            if (canFit) {
                for (int j = i; j < i + needed; j++) {
                    spots.get(j).occupy(vehicle);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean leave(String regNumber) {
        boolean freed = false;

        for (ParkingSpot spot : carSpots) {
            if (!spot.isFree() && spot.getVehicle().getRegNumber().equals(regNumber)) {
                spot.free();
                freed = true;
            }
        }

        for (ParkingSpot spot : truckSpots) {
            if (!spot.isFree() && spot.getVehicle().getRegNumber().equals(regNumber)) {
                spot.free();
                freed = true;
            }
        }
        return freed;
    }

    @Override
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (ParkingSpot spot : carSpots) {
            if (!spot.isFree()) {
                vehicles.add(spot.getVehicle());
            }
        }
        for (ParkingSpot spot : truckSpots) {
            if (!spot.isFree()) {
                vehicles.add(spot.getVehicle());
            }
        }
        return vehicles;
    }

    @Override
    public int getAvailablePlaces() {
        int available = 0;
        for (ParkingSpot spot : carSpots) {
            if (spot.isFree()) {
                available++;
            }
        }
        for (ParkingSpot spot : truckSpots) {
            if (spot.isFree()) {
                available++;
            }
        }
        return available;
    }

}