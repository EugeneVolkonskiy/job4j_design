package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;

        Map<Integer, String> userMap = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (User userCurrent : current) {
            if (!userMap.containsKey(userCurrent.getId())) {
                added++;
            } else if (!userMap.get(userCurrent.getId()).equals(userCurrent.getName())) {
                changed++;
            }
            userMap.remove(userCurrent.getId());
        }
        deleted = userMap.size();
        return new Info(added, changed, deleted);
    }
}
