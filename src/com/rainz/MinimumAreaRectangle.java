package com.rainz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 */
public class MinimumAreaRectangle {
    public static void test(String args[]) {
        int[][] input1 = {{1,1},{1,3},{3,1},{3,3},{2,2}};
        System.out.println(minAreaRect(input1));
        int[][] input2 = {{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
        System.out.println(minAreaRect(input2));
    }
    public static int minAreaRect(int[][] points) {
        Set<List<Integer>> pointSet = new HashSet<>();
        for (int[] p: points) {
            List<Integer> point = new ArrayList<>();
            point.add(p[0]);
            point.add(p[1]);
            pointSet.add(point);
        }
        List<Integer> pLookup = new ArrayList<>();
        pLookup.add(0);
        pLookup.add(1);
        Integer minArea = null;
        // Find two points as diagonal of a rectangle
        for (int i = 0; i < points.length; ++i) {
            int[] p1 = points[i];
            for (int j = i + 1; j < points.length; ++j) {
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1])
                    continue; // same x or y
                // Find the other two points
                pLookup.set(0, p1[0]);
                pLookup.set(1, p2[1]);
                if (!pointSet.contains(pLookup))
                    continue;
                pLookup.set(0, p2[0]);
                pLookup.set(1, p1[1]);
                if (!pointSet.contains(pLookup))
                    continue;
                int area = Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
                if (minArea == null || area < minArea)
                    minArea = area;
            }
        }
        return minArea != null ? minArea : 0;
    }
}
