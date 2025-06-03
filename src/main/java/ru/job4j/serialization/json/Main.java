package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(false, 130000, "Audi", new Engine(116, "1.4 ATM"),
                new String[] {"2022 - Body repair", "2023 - Engine repair"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"isElectric\":false,"
                        + "\"mileage\":140000,"
                        + "\"model\": \"Audi\","
                        + "\"engine\":"
                        + "{"
                        + "\"horsepower\": 116,"
                        + "\"type\": \"1.4 ATM\""
                        + "},"
                        + "\"serviceHistory\":"
                        + "[\"2022 - Body repair\", \"2023 - Engine repair\", \"2025 - Body repair\"]"
                        + "}";
        final Car personMod = gson.fromJson(carJson, Car.class);
        System.out.println(personMod);
    }
}
