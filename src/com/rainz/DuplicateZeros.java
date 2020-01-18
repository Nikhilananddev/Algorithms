package com.rainz;

/*
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
 * Note that elements beyond the length of the original array are not written.
 * Do the above modifications to the input array in place, do not return anything from your function.
 */
public class DuplicateZeros {
    public static void test(String args[]) {
        int[] input1 = {1,0,2,3,0,4,5,0};
        duplicateZeros(input1);
        Main.printArray(input1);
        int[] input2 = {1,2,3};
        duplicateZeros(input2);
        Main.printArray(input2);
    }

    public static void duplicateZeros(int[] arr) {
        int zeroCount = 0;
        int N = arr.length;
        for (int n: arr)
            if (n == 0)
                ++zeroCount;
        int wPtr = N + zeroCount - 1;
        for (int rPtr = N - 1; rPtr >= 0; --rPtr) {
            int n = arr[rPtr];
            int repeat = n == 0 ? 2 : 1;
            for (int i = 0; i < repeat; ++i) {
                if (wPtr < N)
                    arr[wPtr] = n;
                --wPtr;
            }
        }
    }
}
