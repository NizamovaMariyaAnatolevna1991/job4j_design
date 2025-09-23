package ru.job4j.ood.lsp.parking;

public class SimpleParkingSpot implements ParkingSpot {
    private boolean occupied;
    private Vehicle vehicle;

    @Override
    public boolean isFree() {
        return !occupied;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        this.occupied = true;
        this.vehicle = vehicle;
    }

    @Override
    public void free() {
        this.occupied = false;
        this.vehicle = null;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }
}
