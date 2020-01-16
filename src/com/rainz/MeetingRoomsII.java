package com.rainz;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 */
public class MeetingRoomsII {
    public static void test(String args[]) {
        int[][] input1 = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(input1));
        int[][] input2 = {{7,10},{2,4}};
        System.out.println(minMeetingRooms(input2));
        int[][] input3 = {{13,15},{1,13}};
        System.out.println(minMeetingRooms(input3));
    }

    public static int minMeetingRooms(int[][] intervals) {
        int N = intervals.length;
        Integer[] times = new Integer[2*N];
        for (int i = 0; i < N; ++i) {
            times[2*i] = intervals[i][0];
            times[2*i+1] = -intervals[i][1];
        }
        // If end and start of two meetings are the same, first end, then start
        Arrays.sort(times, (x, y) -> Math.abs(x) != Math.abs(y) ?
                                Integer.compare(Math.abs(x), Math.abs(y)) :
                                Integer.compare(x, y));
        int ans = 0;
        int rooms = 0;
        for (int t: times) {
            if (t >= 0)
                ++rooms;
            else
                --rooms;
            if (rooms > ans)
                ans = rooms;
        }
        return ans;
    }

}
