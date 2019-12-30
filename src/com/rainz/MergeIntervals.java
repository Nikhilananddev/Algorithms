package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.rainz.Main.Interval;

/**
 * Created by Yu on 2/8/2015.
 */
public class MergeIntervals {
    public static void test(String args[]) {
        int[][] test = {{15,18},{1,3},{2,6},{8,10}};
        List<Interval> input = new ArrayList<Interval>();
        for (int[] pair: test)
            input.add(new Interval(pair[0], pair[1]));
        List<Interval> answer = merge(input);
        for (Interval v: answer) {
            System.out.printf("[%d,%d] ", v.start, v.end);
        }
        System.out.println();
    }

    public static List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        List<Interval> answer = new ArrayList<Interval>();
        Interval curr = null;
        for (Interval v: intervals) {
            if (curr == null) {
                curr = v;
                answer.add(curr);
                continue;
            }
            if (v.start <= curr.end) {
                curr.end = Math.max(curr.end, v.end);
                continue;
            }
            curr = v;
            answer.add(curr);
        }
        return answer;
    }
}
