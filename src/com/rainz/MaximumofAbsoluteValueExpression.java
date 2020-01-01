package com.rainz;

/*
 * Given two arrays of integers with equal lengths, return the maximum value of:
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 */

public class MaximumofAbsoluteValueExpression {
    public static void test(String args[]) {
        int[] arr11 = {1,2,3,4};
        int[] arr12 = {-1,4,5,6};
        System.out.println(maxAbsValExpr(arr11, arr12));
        int[] arr21 = {1,-2,-5,0,10};
        int[] arr22 = {0,-2,-1,-7,-4};
        System.out.println(maxAbsValExpr(arr21, arr22));
    }

    /* Three absolute values implies 8 cases, but 4 of them are just opposites of the other 4
     * So there are only 4 cases to consider.
     * arr1[i] - arr1[j] + arr2[i] - arr2[j] + i - j
     * arr1[i] - arr1[j] + arr2[i] - arr2[j] + j - i
     * arr1[i] - arr1[j] + arr2[j] - arr2[i] + i - j
     * arr1[i] - arr1[j] + arr2[j] - arr2[i] + j - i
     * Note that it doesn't matter whether i is before or after j.
     */

    public static int maxAbsValExpr(int[] arr1, int[] arr2) {
        int[][] coeffs = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int N = arr1.length;
        int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] max = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for (int idx = 0; idx < N; ++idx) {
            for (int i = 0; i < 4; ++i) {
                int[] coeff = coeffs[i];
                int val = arr1[idx] + coeff[0]*arr2[idx] + coeff[1]*idx;
                if (val > max[i])
                    max[i] = val;
                if (val < min[i])
                    min[i] = val;
            }
        }
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < 4; ++i) {
            int diff = max[i] - min[i];
            if (diff > maxVal)
                maxVal = diff;
        }

        return maxVal;
    }
}
