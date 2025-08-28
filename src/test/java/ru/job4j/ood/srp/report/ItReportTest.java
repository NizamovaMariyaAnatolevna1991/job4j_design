package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ItReportTest {

    @Test
    public void whenItDepartment() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Masha", now, now, 300);
        Employee worker3 = new Employee("Egor", now, now, 200);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report engine = new ItReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append("Ivan;").append(parser.parse(worker1.getHired()))
                .append(";").append(parser.parse(worker1.getFired()))
                .append(";100.0;").append(System.lineSeparator())
                .append("Masha;").append(parser.parse(worker2.getHired()))
                .append(";").append(parser.parse(worker2.getFired()))
                .append(";300.0;").append(System.lineSeparator())
                .append("Egor;").append(parser.parse(worker3.getHired()))
                .append(";").append(parser.parse(worker3.getFired()))
                .append(";200.0;").append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}
