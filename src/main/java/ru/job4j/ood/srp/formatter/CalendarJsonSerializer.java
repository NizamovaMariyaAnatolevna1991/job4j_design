package ru.job4j.ood.srp.formatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarJsonSerializer implements JsonSerializer<Calendar> {

    private final DateTimeParser<Calendar> parser;

    public CalendarJsonSerializer(DateTimeParser<Calendar> parser) {
        this.parser = parser;
    }

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext jsonSerializationContext) {
        String formattedDate = parser.parse(calendar);
        return new JsonPrimitive(formattedDate);
    }
}
