package ru.job4j.ood.srp.formatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import ru.job4j.ood.srp.model.Employee;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmployeeAdapter extends TypeAdapter<Employee> {
    private final DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public Employee read(JsonReader reader) throws IOException {
        Employee employee = new Employee();
        Calendar calendar = Calendar.getInstance();
        reader.beginObject();
        String fieldName = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }
            if ("name".equals(fieldName)) {
                reader.peek();
                employee.setName(reader.nextString());
            }
            if ("hired".equals(fieldName)) {
                reader.peek();
                try {
                    calendar.setTime(dateFormat.parse(reader.nextString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                employee.setHired(calendar);
            }

            if ("fired".equals(fieldName)) {
                reader.peek();
                try {
                    calendar.setTime(dateFormat.parse(reader.nextString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                employee.setFired(calendar);
            }

            if ("salary".equals(fieldName)) {
                reader.peek();
                employee.setSalary(reader.nextDouble());
            }
        }
        reader.endObject();
        return employee;
    }

    @Override
    public void write(JsonWriter writer, Employee employee) throws IOException {
        writer.beginObject();
        writer.name("name");
        writer.value(employee.getName());
        writer.name("hired");
        writer.value(dateTimeParser.parse(employee.getHired()));
        writer.name("fired");
        writer.value(dateTimeParser.parse(employee.getFired()));
        writer.name("salary");
        writer.value(employee.getSalary());
        writer.endObject();
    }
}

