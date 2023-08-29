package ru.job4j.kiss;

import java.util.*;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findByPredicate(value, comparator, (compare) -> compare > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findByPredicate(value, comparator, (compare) -> compare < 0);
    }

    private  <T> T findByPredicate(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        Iterator<? extends T> i = value.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T next = i.next();
            if (predicate.test(comparator.compare(next, result))) {
                result = next;
            }
        }
        return result;
    }
}
