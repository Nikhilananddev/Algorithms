package com.rainz;

import java.util.Arrays;

public class MinimumIncrementToMakeArrayUnique {
    public static void test(String args[]) {
        int[] input = {3,2,1,2,1,7};
        System.out.println(minimumIncrementToMakeArrayUnique(input)); // expect 6
        int[] input2 = {2,2,2,2,0};
        System.out.println(minimumIncrementToMakeArrayUnique(input2)); // expect 6
    }

    public static int minimumIncrementToMakeArrayUnique(int[] A) {
        Arrays.sort(A);
        int result = 0;

        for (int i = 1; i < A.length; ++i) {
            if (A[i] <= A[i-1]) {
                result += A[i-1] - A[i] + 1;
                A[i] = A[i-1] + 1;
            }
        }

        return result;
    }
}
