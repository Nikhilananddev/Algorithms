package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
 */
public class PathWithMaximumMinimumValue {
    public static void test(String args[]) {
        int[][] input1 = {{5,4,5},{1,2,6},{7,4,6}};
        System.out.println(maximumMinimumPath(input1));
        int[][] input2 = {{2,2,1,2,2,2},{1,2,2,2,1,2}};
        System.out.println(maximumMinimumPath(input2));
        int[][] input3 = {{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}};
        System.out.println(maximumMinimumPath(input3));
    }

    private static boolean hasPath(int[][] A, int minVal) {
        int R = A.length;
        int C = A[0].length;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<int[]> workQ = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        int[] src = {0, 0}, dst = {R-1, C-1};
        workQ.offer(src);
        while (!workQ.isEmpty()) {
            int[] cell = workQ.poll();
            int r = cell[0], c = cell[1];
            if (r < 0 || r >= R || c < 0 || c >= C)
                continue;
            if (visited[r][c] || A[r][c] < minVal)
                continue;
            if (r == dst[0] && c == dst[1])
                return true;
            visited[r][c] = true;
            for (int[] dir: dirs) {
                int[] nextCell = {r+dir[0], c+dir[1]};
                workQ.offer(nextCell);
            }
        }
        return false;
    }

    public static int maximumMinimumPath(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (A[r][c] < lo)
                    lo = A[r][c];
                if (A[r][c] > hi)
                    hi = A[r][c];
            }
        }
        if (hi < A[0][0])
            hi = A[0][0];
        if (hi < A[R-1][C-1])
            hi = A[R-1][C-1];
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (hasPath(A, mid)) {
                if (ans < mid)
                    ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}
