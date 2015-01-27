package com.rainz;

import java.util.Arrays;

/**
 * Created by Yu on 1/27/2015.
 */
public class NextPermutation {
    public static void test(String args[]) {
        int[][] test = {
                {1, 2},
                {2, 1},
                {1, 2, 3},
                {1, 3, 2},
                {2, 1, 3},
                {2, 3, 1},
                {3, 1, 2},
                {3, 2, 1},
                {1, 1, 5},
                {1, 1, 1},
                {5, 1, 5},
                {1, 5, 1, 5},
                {5, 5, 1, 1}
        };
        for (int[] a: test) {
            nextPermutation(a);
            for (int x: a) {
                System.out.print("" + x + " ");
            }
            System.out.println();
        }
    }

    public static void nextPermutation(int[] num) {
        if (num.length < 2)
            return;
        // Scanning from right to left for the lowest "swappable" digit.
        // I.e., the first one which has a large digit to its right.
        int sortStart = 0;
        for (int i = num.length - 2; i >=0; --i) {
            if (num[i] < num[i+1]) {
                // Found the first decrease, which will be our lowest "swappable" digit.
                // Now find the (first) smallest to the right that's greater than current
                // Eg, 958766, need to swap 5 (i=1) with the *first* 6 (i=4) ==> 968756
                int minIdx = i+1;
                for (int j = i+2; j < num.length; ++j) {
                    if (num[j] > num[i] && num[j] < num[minIdx])
                        minIdx = j;
                }
                // Now do the swap
                int tmp = num[i];
                num[i] = num[minIdx];
                num[minIdx] = tmp;
                sortStart = i + 1; // will sort the part after i, 968756=>965678
                break;
            }
        }
        // Finally sort the remaining part
        Arrays.sort(num, sortStart, num.length);
    }
}
