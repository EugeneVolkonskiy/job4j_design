package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.formatter.EmployeeAdapter;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private final Store store;
    private final GsonBuilder builder = new GsonBuilder();

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        builder.registerTypeAdapter(Employee.class, new EmployeeAdapter());
        builder.setPrettyPrinting();
        return builder.create().toJson(store.findBy(filter));
    }
}