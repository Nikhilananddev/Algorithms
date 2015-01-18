package com.rainz;

/**
 * Created by Yu on 1/11/2015.
 */
public class MedianOfTwoSortedArrays {
    public static void test(String args[]) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {1, 2, 5, 6, 10, 18, 21};
        System.out.println(findMedianSortedArrays(a, b));
    }

    // Find kth element, where k is 1-based
    protected static double findkth(int A[], int startA, int lenA, int B[], int startB, int lenB, int k) {
        if (lenA > lenB) {
            return findkth(B, startB, lenB, A, startA, lenA, k);
        }
        if (lenA == 0) {
            return B[startB + k - 1];
        }
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }
        int kA = Math.min(k / 2, lenA);
        int kB = k - kA;
        int elementA = A[startA + kA - 1];
        int elementB = B[startB + kB - 1];
        if (elementA == elementB)
            return elementA;
        if (elementA < elementB) {
            return findkth(A, startA + kA, lenA - kA, B, startB, kB, k - kA);
        }
        else {
            return findkth(A, startA, kA, B, startB + kB, lenB - kB, k - kB);
        }
    }

    public static double findMedianSortedArrays(int A[], int B[]) {
        int total = A.length + B.length;
        int mid = total / 2 + 1;
        if (total % 2 == 1) {
            return findkth(A, 0, A.length, B, 0, B.length, mid);
        } else {
            return (findkth(A, 0, A.length, B, 0, B.length, mid) +
                     findkth(A, 0, A.length, B, 0, B.length, mid-1))/2.0;
        }
    }
}
