package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("123");
        String text1 = "1231 и 1232 и 1233";
        Matcher matcher1 = pattern.matcher(text1);
        String rsl = matcher1.replaceAll("Job4j");
        System.out.println(rsl);
        while (matcher1.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher1.start()
            + " iEnd: " + matcher1.end());
        }

        String text2 = "job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.find();
        System.out.println(isPresent2);

    }
}
