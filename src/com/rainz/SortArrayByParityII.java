package com.rainz;

import java.util.Arrays;

public class SortArrayByParityII {

    public static void test(String args[]) {
        int[] input = {3, 1, 2, 4};
        System.out.println(Arrays.toString(sortArrayByParityII(input)));
    }

    public static int[] sortArrayByParityII(int[] A) {
        int[] result = new int[A.length];
        int evenPtr = 0;
        int oddPtr = 1;
        for (int x : A) {
            if (x % 2 == 0) {
                result[evenPtr] = x;
                evenPtr += 2;
            } else {
                result[oddPtr] = x;
                oddPtr += 2;
            }
        }
        return result;
    }
}