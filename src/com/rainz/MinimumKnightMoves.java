package com.rainz;

import java.util.*;

/*
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 * |x| + |y| <= 300
 */
public class MinimumKnightMoves {
    public static void test(String args[]) {
        System.out.println(minKnightMoves(2, 1));
        System.out.println(minKnightMoves(5, 5));
        System.out.println(minKnightMoves(1, 1));
        System.out.println(minKnightMoves(0, 0));
        System.out.println(minKnightMoves(270, -21));
    }

    private static int makeKey(int[] a) {
        return a[0]*1000 + a[1];
    }

    public static int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][] {{2,1}, {2, -1}, {-2,1}, {-2, -1},  {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        List<int[]> currLevel = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int[] start = {0, 0};
        currLevel.add(start);
        visited.add(makeKey(start));
        int steps = 0;
        while (!currLevel.isEmpty()) {
            List<int[]> nextLevel = new ArrayList<>();
            for (int[] cell: currLevel) {
                if (cell[0] == x && cell[1] == y) {
                    return steps;
                }
                for (int[] dir : dirs) {
                    int[] next = {cell[0] + dir[0], cell[1] + dir[1]};
                    int nextKey = makeKey(next);
                    if (visited.contains(nextKey))
                        continue;
                    visited.add(nextKey);
                    nextLevel.add(next);
                }
            }
            ++steps;
            currLevel = nextLevel;
        }
        return -1;
    }
}
