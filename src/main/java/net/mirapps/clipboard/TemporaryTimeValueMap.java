package net.mirapps.clipboard;

import java.util.HashMap;
import java.util.Map;

public class TemporaryTimeValueMap {
    private static Map<Integer, Long> updateTimeMap = new HashMap<>();

    public static Long getUpdateTime(Integer id) {
        return updateTimeMap.get(id);
    }

    public static void putUpdateTime(Integer id, Long updateTime) {
        updateTimeMap.put(id, updateTime);
    }
}
