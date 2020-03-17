package com.rainz;

/*
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels
 */

import java.util.LinkedList;
import java.util.Queue;

public class SmallestRectangleEnclosingBlackPixels {
    public static void test(String args[]) {
        char[][] input1 = {
                {'0','0','1','0'},
                {'0','1','1','0'},
                {'0','1','0','0'}
        };
        System.out.println(minArea(input1, 0, 2));
    }

    public static int minArea(char[][] image, int x, int y) {
        int X = image.length, Y = image[0].length;
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int[] start = {x, y};
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<int[]> workQ = new LinkedList<>();
        workQ.add(start);
        boolean[][] visited = new boolean[X][Y];
        while (!workQ.isEmpty()) {
            int[] pix = workQ.poll();
            int xc = pix[0], yc = pix[1];
            if (xc < 0 || xc >= X || yc < 0 || yc >= Y || image[xc][yc] == '0' || visited[xc][yc])
                continue;
            visited[xc][yc] = true;
            if (xc < minX)
                minX = xc;
            if (xc > maxX)
                maxX = xc;
            if (yc < minY)
                minY = yc;
            if (yc > maxY)
                maxY = yc;
            for (int[] d: dirs) {
                int[] nextPix = {xc + d[0], yc + d[1]};
                workQ.add(nextPix);
            }
        }
        return (maxX - minX + 1) * (maxY - minY + 1);
    }

}
