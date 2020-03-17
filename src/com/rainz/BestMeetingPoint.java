package com.rainz;

/*
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestMeetingPoint {
    public static void test(String args[]) {
        int[][] input1 = {
                {1,  0,  0,  0,  1},
                {0,  0,  0,  0,  0},
                {0,  0,  1,  0,  0}
        };
        System.out.println(minTotalDistance(input1));
    }


    /*
     * Manhattan dist is just sum of 2 dimensions, we just need to add results for the two 1-D cases.
     * In 1-D case, best meeting point is around the median point:
     * If even: A---B---*--C------D
     * Meeting point (*) is always somewhere between B & C, so total dist is dist(B, C) + dist(A, D)
     * If Odd: A---B---X(*)--C------D
     * Meeting point (*) is always X the median, X doesn't have to move, so total dist is dist(B, C) + dist(A, D)
     * So min total distance can be computed by adding distances between first & last, 2nd & 2nd last, ..., etc.
     */
    private static int min1D(List<Integer> coords) {
        int res = 0;
        Collections.sort(coords);
        int i = 0, j = coords.size() - 1;
        while (i < j)
            res += coords.get(j--) - coords.get(i++);
        return res;
    }

    public static int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c] != 0) {
                    rows.add(r);
                    cols.add(c);
                }
            }
        }
        return min1D(rows) + min1D(cols);
    }
}
