package com.rainz;

/**
 * Created by Yu on 2/3/2015.
 */
public class FirstMissingPositive {
    public static void test(String args[]) {
        int[] test = {3,4,-1,1};
        //int[] test = {1,2,0};
        System.out.println(firstMissingPositive(test));
    }

    public static int firstMissingPositive(int[] A) {
        int idx = 0;
        while (idx < A.length) {
            int num = A[idx];
            if (num == idx + 1 || num <= 0) {
                ++idx;
                continue;
            }
            if (num > A.length) {
                A[idx++] = -1;
                continue;
            }
            if (A[num-1] == A[idx]) {
                A[idx++] = -1;
                continue;
            }
            int tmp = A[num-1];
            A[num-1] = A[idx];
            A[idx] = tmp;
        }
        for (idx = 0; idx < A.length; ++idx) {
            if (A[idx] <= 0)
                break;
        }
        return idx+1;
    }

}
