package com.rainz;

/*
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeetingScheduler {
    public static void test(String args[]) {
        int[][] slots1 = {{10,50},{60,120},{140,210}}, slots2 = {{0,15},{60,70}};
        System.out.println(minAvailableDuration(slots1, slots2, 8));
        System.out.println(minAvailableDuration(slots1, slots2, 12));
    }
    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(slots1, Comparator.comparingInt(x->x[0]));
        Arrays.sort(slots2, Comparator.comparingInt(x->x[0]));
        int i1 = 0, i2 = 0;
        while (i1 < slots1.length && i2 < slots2.length) {
            int[] s1 = slots1[i1];
            int[] s2 = slots2[i2];
            if (s1[1] < s2[0]) {
                ++i1;
                continue; // no overlap
            }
            if (s1[0] > s2[1]) {
                ++i2;
                continue; // no overlap
            }
            int end = Math.min(s1[1], s2[1]);
            int start = Math.max(s1[0], s2[0]);
            if (end - start >= duration) {
                ans.add(start);
                ans.add(start+duration);
                return ans;
            }
            if (s1[1] <= s2[1])
                ++i1;
            else
                ++i2;
        }
        return ans;
    }
}
