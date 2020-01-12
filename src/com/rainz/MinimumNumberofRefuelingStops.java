package com.rainz;

/*
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 * station array is sorted by distance.
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumNumberofRefuelingStops {
    public static void test(String args[]) {
        int[][] input1 = {};
        System.out.println(minRefuelStops(1, 1, input1));
        int[][] input2 = {{10, 100}};
        System.out.println(minRefuelStops(100, 1, input2));
        int[][] input3 = {{10,60},{20,30},{30,30},{60,40}};
        System.out.println(minRefuelStops(100, 10, input3));
    }

    /*
     * Drive as far as possible with current tank while recording passed stations.
     * When tank is empty, pick the station with most gas and continue.
     * Repeat until you reach the end.
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> pq = new PriorityQueue<>((x, y)->y-x);
        int ans = 0;
        int stIdx = 0;
        int currPos = startFuel;
        while (currPos < target) {
            while (stIdx < stations.length && currPos >= stations[stIdx][0]) {
                pq.add(stations[stIdx][1]);
                ++stIdx;
            }
            if (pq.size() == 0)
                return -1;
            currPos += pq.remove();
            ++ans;
        }
        return ans;
    }
}
