package com.rainz;

/*
 * Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
 * If there is no common element, return -1.
 */

import java.util.HashMap;
import java.util.Map;

public class FindSmallestCommonElementinAllRows {
    public static void test(String args[]) {
        int[][] input1 = {{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}};
        System.out.println(smallestCommonElement(input1));
    }

    /*
     * Since there is no duplicate in each row, common elements will show up exactly R times in mat, where R is # rows.
     */
    public static int smallestCommonElement(int[][] mat) {
        int R = mat.length;
        if (R == 0)
            return -1;
        int C = mat[0].length;
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int c = 0; c < C; ++c) {
            for (int r = 0; r < R; ++r) {
                int num = mat[r][c];
                int freq = freqs.getOrDefault(num, 0) + 1;
                if (freq == R)
                    return num;
                freqs.put(num, freq);
            }
        }
        return -1;
    }
}
