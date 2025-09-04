package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import ru.job4j.ood.srp.formatter.CalendarAdapter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report {

    private final Store store;
    private final JAXBContext context;
    private final DateTimeParser<Calendar> parser;

    public XmlReport(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
        try {
            this.context = JAXBContext.newInstance(Employees.class);
        } catch (JAXBException e) {
            throw new IllegalStateException("Не удалось инициализировать JAXBContext", e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : store.findBy(filter)) {
            employees.add(emp);
        }

        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            marshaller.setAdapter(new CalendarAdapter(parser));

            StringWriter writer = new StringWriter();
            marshaller.marshal(new Employees(employees), writer);
            return writer.toString();

        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new IllegalStateException("Ошибка сериализации в XML", e);
        }
    }
}
