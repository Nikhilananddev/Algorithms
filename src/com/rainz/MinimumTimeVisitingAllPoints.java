package com.rainz;

public class MinimumTimeVisitingAllPoints {
    public static void test(String args[]) {
        int[][] input = { {1,1}, {3,4},{-1,0}};
        System.out.println(minimumTimeVisitingAllPoints(input));
    }

    public static int minimumTimeVisitingAllPoints(int[][] points) {
        int result = 0;
        for (int i = 1; i < points.length; ++i) {
            int[] prev = points[i-1];
            int[] curr = points[i];
            int dx = Math.abs(curr[0]-prev[0]);
            int dy = Math.abs(curr[1]-prev[1]);
            if (dx >= dy) {
                result += dx; // dy + (dx - dy)
            } else {
                result += dy; // dx + (dy - dx)
            }
        }
        return result;
    }
}
