package com.rainz;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 */
public class MeetingRooms {
    public static void test(String args[]) {
        int[][] input1 = {{0,30},{5,10},{15,20}};
        System.out.println(canAttendMeetings(input1));
        int[][] input2 = {{7,10},{2,4}};
        System.out.println(canAttendMeetings(input2));
    }

    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> (x[0] != y[0] ? Integer.compare(x[0], y[0]) : Integer.compare(x[1], y[1])));
        for (int i = 1; i < intervals.length; ++i) {
            int[] curr = intervals[i];
            int[] prev = intervals[i-1];
            if (curr[0] < prev[1])
                return false;
        }
        return true;
    }
}
