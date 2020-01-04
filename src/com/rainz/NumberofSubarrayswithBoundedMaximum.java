package com.rainz;

public class NumberofSubarrayswithBoundedMaximum {
    public static void test(String args[]) {
        int[] input1 = {2, 1, 4, 3};
        System.out.println(numSubarrayBoundedMax(input1, 2, 3));
        int[] input2 = {73,55,36,5,55,14,9,7,72,52};
        System.out.println(numSubarrayBoundedMax(input2, 32, 69));
    }

    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        int prevCount = 0;
        int ans = 0;
        int start = 0; // start of window where everything is <= R
        for (int i = 0; i < A.length; ++i) {
            int n = A[i];
            int count = 0; // # bounded subarrays ending here
            if (n > R) {
                count = 0;
                start = i+1;
            } else if (n < L) {
                count = prevCount; // extensions of prev subarrays
            }
            else {
                // n is in range, and everything before it is <= R
                // So count every subarray within [start, i]
                count = i - start + 1;
            }
            ans += count;
            prevCount = count;

        }
        return ans;
    }
}
