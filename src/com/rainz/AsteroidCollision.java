package com.rainz;

/*
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */

import java.util.*;

public class AsteroidCollision {
    public static void test(String args[]) {
        int[] input1 = {5, 10, -5};
        Main.printArray(asteroidCollision(input1));
        int[] input2 = {8, -8};
        Main.printArray(asteroidCollision(input2));
        int[] input3 = {10, 2, -5};
        Main.printArray(asteroidCollision(input3));
        int[] input4 = {-2, -1, 1, 2};
        Main.printArray(asteroidCollision(input4));
    }

    public static int[] asteroidCollision(int[] asteroids) {
        List<Integer> output = new ArrayList<>();
        Deque<Integer> rightMoving = new LinkedList<>();

        for (Integer a: asteroids) {
            if (a > 0)
                rightMoving.addLast(a);
            else {
                while (!rightMoving.isEmpty() && a != null) {
                    int r = rightMoving.removeLast();
                    if (r > -a) {
                        rightMoving.addLast(r); // a explodes, put r back
                        a = null;
                    }
                    else if (r == -a) {
                        // both r and a explode
                        a = null;
                    }
                }
                if (a != null) {
                    // All right-moving asteroids are destroyed while this left moving one survived.
                    output.add(a);
                    continue;
                }
            }
        }
        output.addAll(rightMoving);
        int[] result = new int[output.size()];
        for (int i = 0; i < result.length; ++i)
            result[i] = output.get(i);
        return result;
    }
}
