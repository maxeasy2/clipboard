package net.mirapps.clipboard;

import java.util.LinkedHashMap;

public class TemporaryMap extends LinkedHashMap<Integer, String> {
    public TemporaryMap(int objectCount) {
        super(objectCount);
    }

    @Override
    public String put(Integer key, String value) {
        long timeMillis = System.currentTimeMillis();
        TemporaryTimeValueMap.putUpdateTime(key, timeMillis);
        return super.put(key, value);
    }
}