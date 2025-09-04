package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JsonReportTest {

    @Test
    void whenGenerateJsonReportThenCorrectFormat() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Masha", now, now, 300);
        Employee worker3 = new Employee("Egor", now, now, 200);

        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report engine = new JsonReport(store, parser);

        String expected = "[\n"
                + "  {\n"
                + "    \"name\": \"Ivan\",\n"
                + "    \"hired\": \"" + parser.parse(now) + "\",\n"
                + "    \"fired\": \"" + parser.parse(now) + "\",\n"
                + "    \"salary\": 100.0\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"Masha\",\n"
                + "    \"hired\": \"" + parser.parse(now) + "\",\n"
                + "    \"fired\": \"" + parser.parse(now) + "\",\n"
                + "    \"salary\": 300.0\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": \"Egor\",\n"
                + "    \"hired\": \"" + parser.parse(now) + "\",\n"
                + "    \"fired\": \"" + parser.parse(now) + "\",\n"
                + "    \"salary\": 200.0\n"
                + "  }\n"
                + "]";

        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }
}