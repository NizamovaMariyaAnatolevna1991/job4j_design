package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {

    @Test
    void whenGenerateXmlReportThenCorrectFormat() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Masha", now, now, 300);
        Employee worker3 = new Employee("Egor", now, now, 200);

        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report engine = new XmlReport(store, parser);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employee>\n"
                + "        <name>Ivan</name>\n"
                + "        <hired>" + parser.parse(now) + "</hired>\n"
                + "        <fired>" + parser.parse(now) + "</fired>\n"
                + "        <salary>100.0</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>Masha</name>\n"
                + "        <hired>" + parser.parse(now) + "</hired>\n"
                + "        <fired>" + parser.parse(now) + "</fired>\n"
                + "        <salary>300.0</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>Egor</name>\n"
                + "        <hired>" + parser.parse(now) + "</hired>\n"
                + "        <fired>" + parser.parse(now) + "</fired>\n"
                + "        <salary>200.0</salary>\n"
                + "    </employee>\n"
                + "</employees>\n";

        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }
}