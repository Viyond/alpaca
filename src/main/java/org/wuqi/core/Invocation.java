package org.wuqi.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Invocation {

    private ConcurrentMap<String, Object> attributeMap = new ConcurrentHashMap<>();

    public void put(String key, Object value){
        attributeMap.put(key, value);
    }

    public Object get(String key){
        return attributeMap.get(key);
    }
}
