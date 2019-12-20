package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    public static void test(String args[]) {
        int input [][] = {{1,1,1},{1,1,0},{1,0,1}};
        Main.printArray2D(floodFill(input, 1, 1, 2));
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int rows = image.length;
        if (rows == 0)
            return image;
        int cols = image[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> workQ = new LinkedList<>();
        int[] start = {sr, sc};
        int startColor = image[sr][sc];
        workQ.add(start);
        while (!workQ.isEmpty()) {
            int[] cell = workQ.remove();
            int r = cell[0];
            int c = cell[1];
            image[r][c] = newColor;
            visited[r][c] = true;
            for (int[] dir: dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                if (newR >= 0 && newR < rows &&
                    newC >= 0 && newC < cols &&
                    !visited[newR][newC] &&
                    image[newR][newC] == startColor)
                {
                    int[] newCell = {newR, newC};
                    workQ.add(newCell);
                }
            }
        }
        return image;
    }
}
