package com.rainz;

/*
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 */

import java.util.TreeMap;
import java.util.Map;

public class CarPooling {
    public static void test(String args[]) {
        int[][] input1 = {{2,1,5},{3,3,7}};
        System.out.println(carPooling(input1, 4));
        int[][] input2 = {{2,1,5},{3,3,7}};
        System.out.println(carPooling(input2, 5));
        int[][] input3 = {{2,1,5},{3,5,7}};
        System.out.println(carPooling(input3, 3));
        int[][] input4 = {{3,2,7},{3,7,9},{8,3,9}};
        System.out.println(carPooling(input4, 11));
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> stations = new TreeMap<>();
        for (int[] trip: trips) {
            int count = trip[0];
            int begin = trip[1];
            int end = trip[2];
            Integer passengers = stations.get(begin);
            if (passengers == null)
                passengers = 0;
            stations.put(begin, passengers+count);
            passengers = stations.get(end);
            if (passengers == null)
                passengers = 0;
            stations.put(end, passengers-count);
        }
        for (int s: stations.keySet()) {
            capacity -= stations.get(s);
            if (capacity < 0)
                return false;
        }
        return true;
    }
}
