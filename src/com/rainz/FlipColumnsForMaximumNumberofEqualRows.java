package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
 * Return the maximum number of rows that have all values equal after some number of flips.
 */
public class FlipColumnsForMaximumNumberofEqualRows {
    public static void test(String args[]) {
        int[][] input1 = {{0,1},{1,1}};
        System.out.println(maxEqualRowsAfterFlips(input1));
        int[][] input2 = {{0,1},{1,0}};
        System.out.println(maxEqualRowsAfterFlips(input2));
        int[][] input3 = {{0,0,0},{0,0,1},{1,1,0}};
        System.out.println(maxEqualRowsAfterFlips(input3));
    }

    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        /* Rows ending up with all 0's or 1s were the same or completely opposite when started.
           So create a key for each row and its opposite and store them in hash table.
         */
        Map<String, Integer> freqs = new HashMap<>();
        int result = 0;
        for (int[] row: matrix) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int n: row) {
                sb.append(n);
                sb2.append(1 - n);
            }
            String key = sb.toString();
            Integer freq = freqs.get(key);
            if (freq == null)
                freq = 0;
            ++freq;
            if (freq > result)
                result = freq;
            freqs.put(key, freq);

            key = sb2.toString();
            freq = freqs.get(key);
            if (freq == null)
                freq = 0;
            ++freq;
            if (freq > result)
                result = freq;
            freqs.put(key, freq);
        }
        return result;
    }
}
