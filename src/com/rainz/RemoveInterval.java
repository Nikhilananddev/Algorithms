package com.rainz;

/*
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 * Return a sorted list of intervals after all such removals.
 */

import java.util.ArrayList;
import java.util.List;

public class RemoveInterval {
    public static void test(String args[]) {
        int[][] intervals1 = {{0,2},{3,4},{5,7}};
        int[] toBeRemoved1 = {1,6};
        Main.printList2D(removeInterval(intervals1, toBeRemoved1));
        int[][] intervals2 = {{0,5}};
        int[] toBeRemoved2 = {2,3};
        Main.printList2D(removeInterval(intervals2, toBeRemoved2));
    }

    public static List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] interval: intervals) {
            if (interval[1] <= toBeRemoved[0] || interval[0] >= toBeRemoved[1]) {
                // No intersection with removed interval, so keep it
                List<Integer> i = new ArrayList<>();
                i.add(interval[0]);
                i.add(interval[1]);
                ans.add(i);
                continue;
            }
            if (interval[0] >= toBeRemoved[0] && interval[1] <= toBeRemoved[1]) {
                // Completely within removed interval, just drop it
                continue;
            }
            if (interval[0] < toBeRemoved[0] && interval[1] > toBeRemoved[1]) {
                // Completely covers removed interval, plus extra at both ends
                List<Integer> i = new ArrayList<>();
                i.add(interval[0]);
                i.add(toBeRemoved[0]);
                ans.add(i);
                i = new ArrayList<>();
                i.add(toBeRemoved[1]);
                i.add(interval[1]);
                ans.add(i);
                continue;
            }
            if (interval[0] < toBeRemoved[0]) {
                // Right side of interval is removed
                List<Integer> i = new ArrayList<>();
                i.add(interval[0]);
                i.add(toBeRemoved[0]);
                ans.add(i);
                continue;
            }
            if (interval[1] > toBeRemoved[1]) {
                // Left side of interval is removed
                List<Integer> i = new ArrayList<>();
                i.add(toBeRemoved[1]);
                i.add(interval[1]);
                ans.add(i);
                continue;
            }
        }
        return ans;
    }
}
