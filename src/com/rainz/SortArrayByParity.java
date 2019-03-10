package com.rainz;

import java.util.Arrays;

public class SortArrayByParity {
    public static void test(String args[]) {
        int[] input = {3,1,2,4};
        System.out.println(Arrays.toString(sortArrayByParity(input)));
    }

    public static int[] sortArrayByParity(int[] A) {
        int evenPtr = 0;
        int oddPtr = A.length - 1;
        int[] result = new int[A.length];

        for (int x: A) {
            if (x % 2 == 0) {
                result[evenPtr++] = x;
            }
            else {
                result[oddPtr--] = x;
            }
        }

        return result;
    }
}
