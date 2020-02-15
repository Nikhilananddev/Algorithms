package com.rainz;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/*
 * Design a HashMap without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 */
public class DesignHashMap {
    public static void test(String args[]) {
        DesignHashMap hashMap = new DesignHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));            // returns 1
        System.out.println(hashMap.get(3));            // returns -1 (not found)
        hashMap.put(2, 1);          // update the existing value
        System.out.println(hashMap.get(2));            // returns 1
        hashMap.remove(2);          // remove the mapping for 2
        System.out.println(hashMap.get(2));            // returns -1 (not found)
        hashMap.put(11, 110);
        hashMap.put(12, 120);
        hashMap.put(13, 130);
        hashMap.put(14, 140);
        hashMap.put(15, 150);
        System.out.println(hashMap.get(11));            // returns 110
    }

    static class Entry {
        int key;
        int val;
        Entry(int k, int v) { key = k; val = v; }
    }

    private int _capacity = 16;
    private List<Entry>[] _entries = new List[_capacity];
    private int size = 0;
    private int entryCount = 0;
    private static double LOAD_FACTOR = 0.5;

    private int hash(int k) {
        return k;
    }

    private void rehash() {
        List<Entry>[] old = _entries;
        _entries = new List[_capacity];
        size = 0;
        entryCount = 0;
        for (List<Entry> l: old) {
            if (l == null)
                continue;
            for (Entry e: l) {
                put(e.key, e.val);
            }
        }
    }

    public DesignHashMap() {

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int idx = hash(key) % _capacity;
        if (_entries[idx] == null) {
            _entries[idx] = new LinkedList<>();
            ++entryCount;
        }
        for (Entry e: _entries[idx]) {
            if (e.key == key) {
                e.val = value;
                return;
            }
        }
        Entry entry = new Entry(key, value);
        _entries[idx].add(entry);
        ++size;
        if ((double)entryCount / _capacity > LOAD_FACTOR) {
            _capacity *= 2;
            rehash();
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = hash(key) % _capacity;
        List<Entry> l = _entries[idx];
        if (l == null)
            return -1;
        for (Entry e: l) {
            if (e.key == key)
                return e.val;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = hash(key) % _capacity;
        List<Entry> l = _entries[idx];
        if (l == null)
            return;
        ListIterator<Entry> lit = l.listIterator();
        while (lit.hasNext()) {
            Entry e = lit.next();
            if (e.key == key) {
                lit.remove();
                return;
            }
        }
    }
}
