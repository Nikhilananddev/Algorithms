package com.rainz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    public static void test(String args[]) {
        List<int[]> result;
//        int[][] input = {
//                {1, 2, 2, 3, 5},
//                {3, 2, 3, 4, 4},
//                {2, 4, 5, 3, 1},
//                {6, 7, 1, 4, 5},
//                {5, 1, 1, 2, 4}
//        };
//        result = pacificAtlanticWaterFlow(input);
//        for (int[] p: result)
//            System.out.println(String.format("(%d, %d)", p[0], p[1]));

        int[][] input2 = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5},
        };
        result = pacificAtlanticWaterFlow(input2);
        for (int[] p: result)
            System.out.println(String.format("(%d, %d)", p[0], p[1]));
    }

    static void bfs(int[][] matrix, int[][] visited, Queue<int[]> workQ, int ocean) {
        int R = matrix.length;
        int C = matrix[0].length;
        final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!workQ.isEmpty()) {
            int[] point = workQ.remove();
            int r = point[0];
            int c = point[1];
            if ((visited[r][c] & ocean) != 0)
                continue;
            visited[r][c] |= ocean;
            for (int i = 0; i < dir.length; ++i) {
                int newR = r + dir[i][0];
                int newC = c + dir[i][1];
                if (newR >= 0 && newR < R && newC >= 0 && newC < C
                    && matrix[newR][newC] >= matrix[r][c])
                {
                    int[] p = {newR, newC};
                    workQ.add(p);
                }
            }
        }
    }

    public static List<int[]> pacificAtlanticWaterFlow(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        int R = matrix.length;
        if (R <= 0)
            return result;
        int C = matrix[0].length;

        int[][] visited = new int[R][C];
        final int PACIFIC = 1;
        final int ATLANTIC = 1 << 1;

        Queue<int[]> workQ = new LinkedList<>();
        // Pacific
        for (int i = 0; i < R; ++i) {
            int[] startP = {i, 0};
            workQ.add(startP);
        }
        for (int i = 1; i < C; ++i) { // start from 1 to avoid adding (0,0) again
            int[] startP = {0, i};
            workQ.add(startP);
        }
        bfs(matrix, visited, workQ, PACIFIC);

        // Atlantic
        workQ.clear();
        for (int i = 0; i < R; ++i) {
            int[] startP = {i, C-1};
            workQ.add(startP);
        }
        for (int i = 0; i < C-1; ++i) { // ends at C-2 to avoid adding (R-1, C-1) again
            int[] startP = {R-1, i};
            workQ.add(startP);
        }
        bfs(matrix, visited, workQ, ATLANTIC);

        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c)
                if ((visited[r][c] & (PACIFIC|ATLANTIC)) == (PACIFIC|ATLANTIC)) {
                    int[] p = {r, c};
                    result.add(p);
                }

        return result;
    }
}
