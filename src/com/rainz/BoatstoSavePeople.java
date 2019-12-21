package com.rainz;

import java.util.Arrays;

/*
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
 */
public class BoatstoSavePeople {
    public static void test(String args[]) {
        int[] input1 = {1,2};
        int[] input2 = {3,2,2,1};
        int[] input3 = {3,5,3,4};
        System.out.println(numRescueBoats(input1, 3));
        System.out.println(numRescueBoats(input2, 3));
        System.out.println(numRescueBoats(input3, 5));
    }

    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int result = 0;
        int first = 0;
        int last = people.length - 1;
        while (first <= last) {
            if (people[first] + people[last] <= limit)
                ++first;
            --last;
            ++result;
        }
        return result;
    }
}
