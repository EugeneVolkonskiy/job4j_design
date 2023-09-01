package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXMLTest {

    @Test
    public void whenReportXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report reportXML = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append("        <name>").append(worker1.getName()).append("</name>\n")
                .append("        <hired>").append(parser.parse(worker1.getHired())).append("</hired>\n")
                .append("        <fired>").append(parser.parse(worker1.getFired())).append("</fired>\n")
                .append("        <salary>").append(worker1.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("    <employee>\n")
                .append("        <name>").append(worker2.getName()).append("</name>\n")
                .append("        <hired>").append(parser.parse(worker2.getHired())).append("</hired>\n")
                .append("        <fired>").append(parser.parse(worker2.getFired())).append("</fired>\n")
                .append("        <salary>").append(worker2.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(reportXML.generate(em -> true)).isEqualTo(expect.toString());
    }
}