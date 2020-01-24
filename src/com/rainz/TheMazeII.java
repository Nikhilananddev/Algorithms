package com.rainz;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 */
public class TheMazeII {
    public static void test(String args[]) {
        int[][] maze1 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start1 = {0, 4}, dest1 = {4, 4};
        System.out.println(shortestDistance(maze1, start1, dest1));
        int[][] maze2 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start2 = {0, 4}, dest2 = {3, 2};
        System.out.println(shortestDistance(maze2, start2, dest2));
    }

    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int R = maze.length;
        if (R == 0)
            return -1;
        int C = maze[0].length;
        boolean[][] visited = new boolean[R][C];
        int[][] dist = new int[R][C];
        for (int[] row: dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        visited[start[0]][start[1]] = true;

        Queue<int[]> workQ = new PriorityQueue<>(Comparator.comparingInt(x->dist[x[0]][x[1]]));
        workQ.add(start);
        while (!workQ.isEmpty()) {
            int[] cell = workQ.remove();
            int r = cell[0], c = cell[1];
            if (r == destination[0] && c == destination[1])
                return dist[r][c];
            visited[r][c] = true;
            for (int[] dir: dirs) {
                int[] nxt = {r, c};
                int moved = 0;
                do {
                    nxt[0] += dir[0]; nxt[1] += dir[1];
                    ++moved;
                } while (nxt[0] >= 0 && nxt[0] < R && nxt[1] >= 0 && nxt[1] < C && maze[nxt[0]][nxt[1]] == 0);
                nxt[0] -= dir[0]; nxt[1] -= dir[1]; --moved;
                int nr = nxt[0], nc = nxt[1];
                if (visited[nr][nc])
                    continue;
                int newDist= dist[r][c] + moved;
                if (newDist < dist[nr][nc]) {
                    dist[nr][nc] = newDist;
                    workQ.add(nxt);
                }
            }
        }
        return -1;
    }

}
