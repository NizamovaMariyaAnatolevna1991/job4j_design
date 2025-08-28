package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class HrReportTest {

    @Test
    public void whenHrDepartment() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Masha", now, now, 300);
        Employee worker3 = new Employee("Egor", now, now, 200);

        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report engine = new HrReport(store);

        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;").append(System.lineSeparator())
                .append("Masha; 300.0;").append(System.lineSeparator())
                .append("Egor; 200.0;").append(System.lineSeparator())
                .append("Ivan; 100.0;").append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}
