package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
 */
public class ShortestDistancetoTargetColor {
    public static void test(String args[]) {
        int[] colors1 = {1,1,2,1,3,2,2,3,3};
        int[][] queries1 = {{1,3},{2,2},{6,1}};
        System.out.println(shortestDistanceColor(colors1, queries1));
        int[] colors2 = {1,2};
        int[][] queries2 = {{0,3}};
        System.out.println(shortestDistanceColor(colors2, queries2));
    }

    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer>[] indices = new List[3];
        for (int i = 0; i < indices.length; ++i)
            indices[i] = new ArrayList<>();
        for (int i = 0; i < colors.length; ++i)
            indices[colors[i]-1].add(i);
        List<Integer> ans = new ArrayList<>();
        for (int[] q: queries) {
            List<Integer> ind = indices[q[1]-1];
            if (ind.size() == 0) {
                ans.add(-1);
                continue;
            }
            int idx = Collections.binarySearch(ind, q[0]);
            int pos = -1;
            if (idx >= 0) {
                pos = ind.get(idx);
            } else {
                idx = -idx - 1;
                int dist1 = idx < ind.size() ? ind.get(idx) - q[0] : Integer.MAX_VALUE;
                int dist2 = idx > 0 ? q[0] - ind.get(idx - 1) : Integer.MAX_VALUE;
                pos = dist1 <= dist2 ? ind.get(idx) : ind.get(idx-1);
            }
            ans.add(Math.abs(q[0]-pos));
        }
        return ans;
    }
}
