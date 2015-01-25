package com.rainz;

/**
 * Created by Yu on 1/25/2015.
 */
public class RemoveDuplicatesfromSortedArray {
    public static void test(String args[]) {
        int[] test = {1, 2, 2, 3, 4, 4, 4, 5, 6, 6, 6, 6, 7, 7};
        int count = removeDuplicates(test);
        for (int i = 0; i < count; ++i)
            System.out.print("" + test[i] + " ");
    }

    public static int removeDuplicates(int[] A) {
        if (A.length < 2)
            return A.length;

        int readIdx = 1, writeIdx = 1;
        while (readIdx < A.length) {
            if (A[readIdx] != A[readIdx-1]) {
                A[writeIdx++] = A[readIdx++];
            } else {
                ++readIdx;
            }
        }
        return writeIdx;
    }
}
