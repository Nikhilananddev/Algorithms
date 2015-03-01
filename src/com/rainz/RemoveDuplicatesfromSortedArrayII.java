package com.rainz;

/**
 * Created by Yu on 3/1/2015.
 */
public class RemoveDuplicatesfromSortedArrayII {
    public static void test(String args[]) {
        int[] test = {1,1,1,2,2,3,4,5,5,5,5,5,5,6,7};
        int len = removeDuplicates(test);
        for (int i = 0; i < len; ++i)
            System.out.print(""+test[i]+" ");
        System.out.println();
    }

    public static int removeDuplicates(int[] A) {
        int dupCount = 0;
        int writePtr = 0;
        for (int i = 0; i < A.length; ++i) {
            if (i == 0 || A[i] != A[i-1]) {
                A[writePtr++] = A[i];
                dupCount = 1;
            } else {
                ++dupCount;
                if (dupCount <= 2)
                    A[writePtr++] = A[i];
            }
        }
        return writePtr;
    }
}
