package com.rainz;

import java.util.LinkedHashMap;

public class LRUCache {
    public static void test(String args[]) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    private LinkedHashMap<Integer, Integer> lhm = null;
    private int cap = 0;

    public LRUCache(int capacity) {
        cap = capacity;
        lhm = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        Integer val = lhm.remove(key); // remove and add later so it becomes the newest
        if (val == null)
            return -1;
        lhm.put(key, val);
        return val;
    }

    public void put(int key, int value) {
        if (lhm.keySet().contains(key)) {
            lhm.remove(key); // remove and add later so it becomes the newest
        } else {
            // New entry
            if (lhm.size() == cap) {
                // Evict oldest
                int firstKey = lhm.keySet().iterator().next();
                lhm.remove(firstKey);
            }
        }
        lhm.put(key, value);
    }
}
