package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJSONTest {

    @Test
    public void whenReportJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report reportJSON = new ReportJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("[\n")
                .append("  {\n")
                .append("    \"name\": \"").append(worker1.getName()).append("\",\n")
                .append("    \"hired\": \"").append(parser.parse(worker1.getHired())).append("\",\n")
                .append("    \"fired\": \"").append(parser.parse(worker1.getFired())).append("\",\n")
                .append("    \"salary\": ").append(worker1.getSalary()).append("\n")
                .append("  },\n")
                .append("  {\n")
                .append("    \"name\": \"").append(worker2.getName()).append("\",\n")
                .append("    \"hired\": \"").append(parser.parse(worker2.getHired())).append("\",\n")
                .append("    \"fired\": \"").append(parser.parse(worker2.getFired())).append("\",\n")
                .append("    \"salary\": ").append(worker2.getSalary()).append("\n")
                .append("  }\n")
                .append("]");
        assertThat(reportJSON.generate(em -> true)).isEqualTo(expect.toString());
    }
}