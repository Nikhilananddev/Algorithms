package com.rainz;

/*
 * Given an array of integers A and let n to be its length.
 * Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * Calculate the maximum value of F(0), F(1), ..., F(n-1).
 */
public class RotateFunction {
    public static void test(String args[]) {
        int[] A = {4, 3, 2, 6};
        System.out.println(maxRotateFunction(A));
    }

    /* Note the increase from F(x) to F(x+1). Use that to simply computation */
    public static int maxRotateFunction(int[] A) {
        int sum = 0;
        int prev = 0;
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            prev += i * A[i];
        }
        int ans = prev;
        for (int i = A.length-1; i >= 1; --i) {
            int val = prev + sum - A.length * A[i];
            if (val > ans)
                ans = val;
            prev = val;
        }
        return ans;
    }
}
