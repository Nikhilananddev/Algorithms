package com.rainz;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/*
 * Design a HashSet without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 */
public class DesignHashSet {
    public static void test(String args[]) {
        DesignHashSet hashSet = new DesignHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));    // returns true
        System.out.println(hashSet.contains(3));    // returns false (not found)
        hashSet.add(2);
        System.out.println(hashSet.contains(2));    // returns true
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));    // returns false (already removed)
    }

    static class Entry {
        int key;
        Entry(int k) { key = k; }
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
                add(e.key);
            }
        }
    }
    public DesignHashSet() {

    }

    public void add(int key) {
        int idx = hash(key) % _capacity;
        if (_entries[idx] == null) {
            _entries[idx] = new LinkedList<>();
            ++entryCount;
        }
        for (Entry e: _entries[idx]) {
            if (e.key == key) {
                return;
            }
        }
        Entry entry = new Entry(key);
        _entries[idx].add(entry);
        ++size;
        if ((double)entryCount / _capacity > LOAD_FACTOR) {
            _capacity *= 2;
            rehash();
        }
    }

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

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int idx = hash(key) % _capacity;
        List<Entry> l = _entries[idx];
        if (l == null)
            return false;
        for (Entry e: l) {
            if (e.key == key)
                return true;
        }
        return false;
    }
}
