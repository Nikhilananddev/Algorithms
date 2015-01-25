package com.rainz;

/**
 * Created by Yu on 1/25/2015.
 */
public class RemoveElement {
    public static void test(String args[]) {
        int[] test = {3,6,3,1,7,3,4,3,3,6,6,7};
        int count = removeElement(test, 3);
        for (int i = 0; i < count; ++i)
            System.out.print("" + test[i] + " ");
    }

    public static int removeElement(int[] A, int elem) {
        int readIdx = 0, writeIdx = 0;
        while (readIdx < A.length) {
            if (A[readIdx] != elem) {
                A[writeIdx++] = A[readIdx++];
            } else {
                ++readIdx;
            }
        }
        return writeIdx;
    }
}
