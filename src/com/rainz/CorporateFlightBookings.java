package com.rainz;

/*
 * There are n flights, and they are labeled from 1 to n.
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 */
public class CorporateFlightBookings {
    public static void test(String args[]) {
        int[][] input1 = {{1,2,10},{2,3,20},{2,5,25}};
        Main.printArray(corpFlightBookings(input1, 5));
    }
    /*
     * Key idea is only to record the change(s) on that day (increases, decreases)
     * Value for current day is simply today_changes + prev_day_value
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        int[] changes = new int[n];
        for (int[] b: bookings) {
            changes[b[0]-1] += b[2];
            if (b[1] < n)
                changes[b[1]] -= b[2];
        }
        for (int i = 0; i < n; ++i) {
            int prev = (i > 0 ? ans[i-1] : 0);
            ans[i] = prev + changes[i];
        }
        return ans;
    }
}
