package ru.job4j.ood.srp.formatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Calendar;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {

    private final DateTimeParser<Calendar> parser;

    public CalendarAdapter(DateTimeParser<Calendar> parser) {
        this.parser = parser;
    }

    @Override
    public Calendar unmarshal(String s) throws Exception {
        return null;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return parser.parse(calendar);
    }
}
