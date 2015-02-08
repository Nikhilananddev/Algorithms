package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Yu on 2/8/2015.
 */
public class InsertInterval {
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static void test(String args[]) {
        int[][] test = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        List<Interval> input = new ArrayList<Interval>();
        for (int[] pair: test)
            input.add(new Interval(pair[0], pair[1]));
        List<Interval> answer = insert(input, new Interval(4, 9));
        for (Interval v: answer) {
            System.out.printf("[%d,%d] ", v.start, v.end);
        }
        System.out.println();
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> answer = new ArrayList<Interval>();
        Interval curr = null;
        for (Interval v: intervals) {
            if (v.end < newInterval.start || v.start > newInterval.end) {
                // No overlap
                if (v.start > newInterval.end && curr == null) {
                    // Special case: no overlap but we see the first larger interval
                    curr = newInterval; // use curr to indicate newInterval has been inserted
                    answer.add(curr); // So add the newInterval
                }
                answer.add(v); // Just add this interval since there's no overlap
                continue;
            }
            // Has overlap
            if (curr == null) {
                // First encounter
                curr = new Interval(Math.min(v.start, newInterval.start),
                                    Math.max(v.end, newInterval.end));
                answer.add(curr);
                continue;
            }
            // Now v.start < curr.start, since curr.start is MIN(old current.start, previous v.start)
            curr.end = Math.max(curr.end, v.end);
        }
        // Special case: newInterval is never inserted
        // List might be empty or newInterval is larger than all intervals in list
        if (curr == null) {
            answer.add(newInterval);
        }
        return answer;
    }
}
