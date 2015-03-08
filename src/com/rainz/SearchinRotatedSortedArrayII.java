package com.rainz;

/**
 * Created by Yu on 3/5/2015.
 */
public class SearchinRotatedSortedArrayII {
    public static void test(String args[]) {
        int[] test = {10,10,10,10,20,20,20,20,20,20,30,30,40,50,50,50};
        System.out.println(search(test, 10));
        System.out.println(search(test, 20));
        System.out.println(search(test, 30));
        System.out.println(search(test, 40));
        System.out.println(search(test, 50));
        System.out.println(search(test, 9));
        System.out.println(search(test, 51));
        System.out.println(search(test, 23));
        System.out.println(search(test, 45));

    }

    public static boolean search(int[] A, int target) {
        int begin = 0, end = A.length - 1; // end is inclusive
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (A[mid] == target)
                return true;
            if (A[mid] > A[begin]) {
                // mid is on left (the higher) branch
                if (target < A[mid] && target >= A[begin])
                    end = mid-1;
                else
                    begin = mid+1;
            } else if (A[mid] < A[begin]) {
                // mid is on right (the lower) branch
                if (target > A[mid] && target <= A[end])
                    begin = mid+1;
                else
                    end = mid-1;
            } else {
                // Now A[mid] == A[begin]
                // Since A[mid] != target, A[begin] != target, so eliminate A[begin]
                ++begin;
            }
            // The case where begin to end is sorted is covered by either of the above cases.
        }
        return false;
    }
}
