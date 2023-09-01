package ru.job4j.ood.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAdapter extends XmlAdapter<String, Calendar> {

    private final DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public String marshal(Calendar dateTime) {
        return dateTimeParser.parse(dateTime);
    }

    @Override
    public Calendar unmarshal(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}


