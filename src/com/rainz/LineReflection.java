package com.rainz;

/*
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LineReflection {
    public static void test(String args[]) {
        int[][] input1 = {{1,1},{-1,1}};
        System.out.println(isReflected(input1));
        int[][] input2 = {{1,1},{-1,-1}};
        System.out.println(isReflected(input2));
    }

    public static boolean isReflected(int[][] points) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        Map<Integer, Set<Integer>> xMap = new HashMap<>();
        for (int[] p: points) {
            if (minX > p[0])
                minX = p[0];
            if (maxX < p[0])
                maxX = p[0];
            xMap.putIfAbsent(p[0], new HashSet<>());
            xMap.get(p[0]).add(p[1]);
        }
        for (int[] p: points) {
            int reflexX = maxX + minX - p[0];
            Set<Integer> ySet = xMap.get(reflexX);
            if (ySet == null || !ySet.contains(p[1]))
                return false;
        }
        return true;
    }

}
