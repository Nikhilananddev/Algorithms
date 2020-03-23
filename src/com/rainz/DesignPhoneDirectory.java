package com.rainz;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Design a Phone Directory which supports the following operations:
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 */
public class DesignPhoneDirectory {
    public static void test(String args[]) {
        // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
        DesignPhoneDirectory directory = new DesignPhoneDirectory(3);

        // It can return any available phone number. Here we assume it returns 0.
        System.out.println(directory.get());

        // Assume it returns 1.
        System.out.println(directory.get());

        // The number 2 is available, so return true.
        System.out.println(directory.check(2));

        // It returns 2, the only number that is left.
        System.out.println(directory.get());

        // The number 2 is no longer available, so return false.
        System.out.println(directory.check(2));

        // Release number 2 back to the pool.
        directory.release(2);

        // Number 2 is available again, return true.
        System.out.println(directory.check(2));
    }

    private int _maxNum;
    private Queue<Integer> nums = new LinkedList<>();
    private Set<Integer> used = new HashSet<>();

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory(int maxNumbers) {
        _maxNum = maxNumbers;
        for (int i = 0; i < maxNumbers; ++i)
            nums.offer(i);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (nums.isEmpty())
            return -1;
        int num = nums.poll();
        used.add(num);
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (used.contains(number)) {
            used.remove(number);
            nums.offer(number);
        }
    }
}
