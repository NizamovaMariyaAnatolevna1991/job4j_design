package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarJsonSerializer;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {

    private final Store store;
    private final Gson gson;

    public JsonReport(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Calendar.class, new CalendarJsonSerializer(parser))
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : store.findBy(filter)) {
            employees.add(emp);
        }
        return gson.toJson(employees);
    }
}
