package com.rainz;

import java.util.*;

public class BrokenCalculator {
    public static void test(String args[]) {
        System.out.println(brokenCalculator(2, 3));
        System.out.println(brokenCalculator(5, 8));
        System.out.println(brokenCalculator(3, 10));
        System.out.println(brokenCalculator(1024, 1));
        System.out.println(brokenCalculator(1, 1000000000));
    }

    public static int brokenCalculatorBFS(int X, int Y) {
        Queue<Long> workQ = new LinkedList<>();
        workQ.add((long)X);
        int steps = 0;
        Set<Long> visited = new HashSet<>();
        while (!workQ.isEmpty()) {
            boolean found = false;
            List<Long> nextLevel = new ArrayList<>();
            do {
                long val = workQ.remove();
                if (val == Y) {
                    found = true;
                    break;
                }
                if (val < Y) {
                    long newVal = val * 2;
                    if (!visited.contains(newVal)) {
                        nextLevel.add(newVal);
                        visited.add(newVal);
                    }
                }
                if (val > Y/2) {
                    long newVal = val - 1;
                    if (!visited.contains(newVal)) {
                        nextLevel.add(newVal);
                        visited.add(newVal);
                    }
                }
            } while (!workQ.isEmpty());
            if (found)
                break;
            workQ.addAll(nextLevel);
            ++steps;
        }
        return steps;
    }

    public static int brokenCalculator(int X, int Y) {
        if (X >= Y)
            return X - Y;

        int steps = 0;
        // # multiply-by-2 steps is fixed. Stop when X just becomes greater than Y.
        while (X < Y) {
            X *= 2;
            ++steps;
        }
        // Now we need "minus-1" steps to cancel out the diff.
        int diff = X - Y;
        // Do subtraction as early as possible to maximize reduction amount
        // If we do "minus-1" first, this will be the actual reduction amount
        int decVal = 1 << steps;
        while (diff > 0) {
            steps += diff / decVal; // reduce as much as possible at this value
            diff %= decVal; // the remaining diff
            decVal >>= 1; // this will be the reduction amount at the next shift
        }
        return steps;
    }
}
