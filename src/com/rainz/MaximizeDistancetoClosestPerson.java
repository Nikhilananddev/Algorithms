package com.rainz;

/*
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to closest person.
 */
public class MaximizeDistancetoClosestPerson {
    public static void test(String args[]) {
        int[] input1 = {1,0,0,0,1,0,1};
        System.out.println(maxDistToClosest(input1));
        int[] input2 = {1,0,0,0};
        System.out.println(maxDistToClosest(input2));
        int[] input3 = {0,1};
        System.out.println(maxDistToClosest(input3));
    }
    public static int maxDistToClosest(int[] seats) {
        int ans = 0;
        int prev1 = -1;
        int N = seats.length;
        for (int i = 0; i < N; ++i) {
            int num = seats[i];
            int dist = -1;
            if (num == 1) {
                dist = prev1 >= 0 ? (i-prev1)/2 : i;
                prev1 = i;
            } else if (i == N - 1) {
                dist = i - prev1;
            }
            if (dist > ans)
                ans = dist;
        }
        return ans;
    }
}
