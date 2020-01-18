package com.rainz;

/*
 * Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.  Return -1 if no such i exists.
 */
public class FixedPoint {
    public static void test(String args[]) {
        int[] input1 = {-10,-5,0,3,7};
        System.out.println(fixedPoint(input1));
        int[] input2 = {0,2,5,8,17};
        System.out.println(fixedPoint(input2));
        int[] input3 = {-10,-5,3,4,7,9};
        System.out.println(fixedPoint(input3));
    }

    public static int fixedPoint(int[] A) {
        int lo = 0, hi = A.length-1;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == mid) {
                if (ans == -1 || ans > mid)
                    ans = mid;
                hi = mid - 1;
            } else if (A[mid] < mid)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return ans;
    }


}
