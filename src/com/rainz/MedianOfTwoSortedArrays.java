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

    public static double findMedianSortedArraysFast(int nums1[], int nums2[]) {
        int total = nums1.length + nums2.length;
        int mid = total / 2 + 1;
        if (total % 2 == 1) {
            return findkth(nums1, 0, nums1.length, nums2, 0, nums2.length, mid);
        } else {
            return (findkth(nums1, 0, nums1.length, nums2, 0, nums2.length, mid) +
                     findkth(nums1, 0, nums1.length, nums2, 0, nums2.length, mid-1))/2.0;
        }
    }

    public static double findMedianSortedArrays2ptr(int nums1[], int nums2[]) {
        int total = nums1.length + nums2.length;
        int mid1 = (total-1) / 2;
        int mid2 = mid1;
        if (total % 2 == 0)
            ++mid2;
        int mid1Val = Integer.MAX_VALUE, mid2Val = Integer.MAX_VALUE;
        int aIdx = 0, bIdx = 0;
        while (aIdx < nums1.length || bIdx < nums2.length) {
            int a = aIdx < nums1.length ? nums1[aIdx] : Integer.MAX_VALUE;
            int b = bIdx < nums2.length ? nums2[bIdx] : Integer.MAX_VALUE;
            int num = Math.min(a, b);
            if (aIdx + bIdx == mid1)
                mid1Val = num;
            if (aIdx + bIdx == mid2) {
                mid2Val = num;
                break;
            }
            if (a <= b)
                ++aIdx;
            else
                ++bIdx;
        }
        return (mid1Val + mid2Val) / 2.0;
    }

    public static double findMedianSortedArrays(int nums1[], int nums2[]) {
        //return findMedianSortedArraysFast(nums1, nums2);
        return findMedianSortedArrays2ptr(nums1, nums2);
    }
}
