package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> prevMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.getId(), user.getName());
        }

        Map<Integer, String> currMap = new HashMap<>();
        for (User user : current) {
            currMap.put(user.getId(), user.getName());
        }

        int added = 0;
        int deleted = 0;
        int changed = 0;

        Set<Integer> allIds = new HashSet<>(prevMap.keySet());
        allIds.addAll(currMap.keySet());

        for (Integer id : allIds) {
            String prevName = prevMap.get(id);
            String currName = currMap.get(id);

            if (prevName == null) {
                added++;
            } else if (currName == null) {
                deleted++;
            } else if (!prevName.equals(currName)) {
                changed++;
            }
        }

        return new Info(added, changed, deleted);
    }
}
