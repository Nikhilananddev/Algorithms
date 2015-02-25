package com.rainz;

/**
 * Created by Yu on 2/24/2015.
 */
public class SortColors {
    public static void test(String args[]) {
        int[] test = {0, 0, 0, 2, 1, 2, 0, 0, 1, 1, 2, 2, 0, 1};
        sortColors(test);
        for (int x: test)
            System.out.print("" + x + " ");
        System.out.println();
    }

    public static void sortColors(int[] A) {
        int readIdx = 0, write0Idx = 0, write2Idx = A.length-1;
        int tmp;
        while (readIdx <= write2Idx) {
            switch (A[readIdx]) {
                case 0:
                    tmp = A[write0Idx];
                    A[write0Idx++] = A[readIdx];
                    A[readIdx]  = tmp;
                    if (readIdx < write0Idx)
                        readIdx = write0Idx;
                    break;
                case 1:
                    ++readIdx;
                    break;
                case 2:
                    tmp = A[write2Idx];
                    A[write2Idx--] = A[readIdx];
                    A[readIdx]  = tmp;
                    break;
            }
        }
    }
}
