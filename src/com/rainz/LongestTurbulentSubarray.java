package com.rainz;

/*
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * Return the length of a maximum size turbulent subarray of A.
 */
public class LongestTurbulentSubarray {
    public static void test(String args[]) {
        int[] input1 = {9,4,2,10,7,8,8,1,9};
        System.out.println(maxTurbulenceSize(input1));
        int[] input2 = {4,8,12,16};
        System.out.println(maxTurbulenceSize(input2));
        int[] input3 = {100};
        System.out.println(maxTurbulenceSize(input3));
    }

    public static int maxTurbulenceSize(int[] A) {
        int N = A.length;
        if (N < 1)
            return N;
        int ans = 1;
        int cmp = 0;
        int len = 1;
        for (int i = 1; i < N; ++i) {
            int newCmp = Integer.compare(A[i], A[i-1]);
            if (cmp != 0 && newCmp == -cmp)
                ++len;
            else
                len = newCmp != 0 ? 2 : 1;
            if (len > ans)
                ans = len;
            cmp = newCmp;
        }
        return ans;
    }

}
