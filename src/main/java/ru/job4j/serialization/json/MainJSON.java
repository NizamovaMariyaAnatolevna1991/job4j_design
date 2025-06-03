package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJSON {
    public static void main(String[] args) {
        JSONObject jsonEngine = new JSONObject("{\"horsepower\":\"116\", \"type\": \"1.4 ATM\"}");

        List<String> list = new ArrayList<>();
        list.add("2022 - Body repair");
        list.add("2023 - Engine repair");
        JSONArray jsonServiceHistories = new JSONArray(list);

        final Car car = new Car(false, 130000, "Audi", new Engine(116, "1.4 ATM"),
                new String[]{"2022 - Body repair", "2023 - Engine repair"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isElectric", car.isElectric());
        jsonObject.put("mileage", car.getMileage());
        jsonObject.put("model", car.getModel());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("serviceHistories", jsonServiceHistories);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}
