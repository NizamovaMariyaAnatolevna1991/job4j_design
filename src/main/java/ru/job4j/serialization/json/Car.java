package ru.job4j.serialization.json;

public class Car {
    private boolean isElectric;
    private long mileage;
    private String model;
    private Engine engine;
    private String[] serviceHistory;

    public Car(boolean isElectric, long mileage, String model, Engine engine, String[] serviceHistory) {
        this.isElectric = isElectric;
        this.mileage = mileage;
        this.model = model;
        this.engine = engine;
        this.serviceHistory = serviceHistory;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String[] getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(String[] serviceHistory) {
        this.serviceHistory = serviceHistory;
    }
}


