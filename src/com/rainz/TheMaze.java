package com.rainz;

/*
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 */
public class TheMaze {
    public static void test(String args[]) {
        int[][] maze1 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start1 = {0, 4}, dest1 = {4, 4};
        System.out.println(hasPath(maze1, start1, dest1));
        int[][] maze2 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start2 = {0, 4}, dest2 = {3, 2};
        System.out.println(hasPath(maze2, start2, dest2));
    }

    private static boolean dfs(int[][] maze, int[] src, int[] dst, boolean[][] visited) {
        int R = maze.length;
        int C = maze[0].length;
        int r = src[0], c = src[1];
        if (r == dst[0] && c == dst[1])
            return true;
        if (r < 0 || r >= R || c < 0 || c >= C)
            return false;
        if (maze[r][c] == 1 || visited[r][c])
            return false;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        visited[r][c] = true;
        for (int[] dir: dirs) {
            int[] nxt = {r, c};
            do {
                nxt[0] += dir[0]; nxt[1] += dir[1];
            } while (nxt[0] >= 0 && nxt[0] < R && nxt[1] >= 0 && nxt[1] < C && maze[nxt[0]][nxt[1]] == 0);
            nxt[0] -= dir[0]; nxt[1] -= dir[1];
            if (dfs(maze, nxt, dst, visited))
                return true;
        }
        // The line below is not needed!
        //visited[r][c] = false;
        return false;
    }

    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze.length == 0)
            return false;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
}
