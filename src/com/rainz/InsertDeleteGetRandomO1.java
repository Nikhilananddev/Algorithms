package com.rainz;

import java.util.*;

/*
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
public class InsertDeleteGetRandomO1 {
    public static void test(String args[]) {
        InsertDeleteGetRandomO1 randomSet = new InsertDeleteGetRandomO1();
        /*
        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.remove(2));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.remove(1));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
        */
        System.out.println(randomSet.remove(0));
        System.out.println(randomSet.remove(0));
        System.out.println(randomSet.insert(0));
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.remove(0));
        System.out.println(randomSet.insert(0));
    }

    private Map<Integer, Integer> numsTable = new HashMap<>();
    private List<Integer> numsArray = new ArrayList<>();
    private Random random = new Random();

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (numsTable.containsKey(val))
            return false;
        numsArray.add(val);
        numsTable.put(val, numsArray.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer removeIdx = numsTable.get(val);
        if (removeIdx == null)
            return false;
        // Copy last value to removeIdx in arraylist and resize
        int lastIdx = numsArray.size() - 1;
        int lastVal = numsArray.get(lastIdx);
        numsArray.set(removeIdx, lastVal);
        numsArray.remove(lastIdx);
        numsTable.put(lastVal, removeIdx); // do this before removing val, because lastVal might equal val
        numsTable.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return numsArray.get(random.nextInt(numsArray.size()));
    }
}
