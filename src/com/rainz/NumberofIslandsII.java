package com.rainz;

/*
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NumberofIslandsII {
    public static void test(String args[]) {
        int[][] input1 = {{0,0}, {0,1}, {1,2}, {2,1}};
        System.out.println(numIslands2(3, 3, input1));
        int[][] input2 = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        System.out.println(numIslands2(3, 3, input2));
    }

    private static int getRootPathCompression(int[] roots, int island) {
        Stack<Integer> stk = new Stack<>();
        int curr = island;
        while (roots[curr] != curr) {
            stk.push(curr);
            curr = roots[curr];
        }
        while (!stk.isEmpty()) {
            int n = stk.pop();
            roots[n] = curr;
        }
        return curr;
    }
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();

        // Each grid cell stores 1-based position ID
        int[][] grid = new int[m][n];

        int N = positions.length;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] roots = new int[N+1]; // 1-based position IDs
        int islands = 0;
        for (int i = 1; i <= N; ++i) {
            int r = positions[i-1][0];
            int c = positions[i-1][1];
            if (grid[r][c] == 0) {
                roots[i] = i;
                ++islands;
                grid[r][c] = i;
                for (int[] d : dirs) {
                    int rr = r + d[0], cc = c + d[1];
                    if (rr < 0 || rr >= m || cc < 0 || cc >= n)
                        continue;
                    int nb = grid[rr][cc];
                    if (nb == 0)
                        continue;
                    int rootCurr = getRootPathCompression(roots, i);
                    int rootNb = getRootPathCompression(roots, nb);
                    if (rootCurr != rootNb) {
                        roots[rootCurr] = rootNb;
                        --islands;
                    }
                }
            }
            ans.add(islands);
        }
        return ans;
    }
}
