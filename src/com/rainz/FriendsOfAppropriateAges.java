package com.rainz;

import java.util.Arrays;

/*
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 * How many total friend requests are made?
 */
public class FriendsOfAppropriateAges {
    public static void test(String args[]) {
        int[] input1 = {16,16};
        System.out.println(numFriendRequests(input1));
        int[] input2 = {16,17,18};
        System.out.println(numFriendRequests(input2));
        int[] input3 = {20,30,100,110,120};
        System.out.println(numFriendRequests(input3));
    }

    public static int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int age: ages) count[age]++;

        int ans = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB)
                    continue;
                if (ageA < ageB)
                    continue;
                ans += countA * countB;
                if (ageA == ageB)
                    ans -= countA;
            }
        }

        return ans;
    }
}
