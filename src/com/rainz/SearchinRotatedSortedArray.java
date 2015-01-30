package com.rainz;

/**
 * Created by Yu on 1/30/2015.
 */
public class SearchinRotatedSortedArray {
    public static void test(String args[]) {
        int[] test = {10,20,30,40,50};
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

    public static int search(int[] A, int target) {
        int begin = 0, end = A.length - 1; // end is inclusive
        if (A.length == 1)
            return (A[0] == target ? 0 : -1);
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (A[mid] == target)
                return mid;
            if (begin == end - 1) {
                // Two elements left. In this case begin == mid, already tested
                if (A[end] == target)
                    return end;
                // Neither begin nor end matches target. Search failed.
                break;
            }
            if (A[mid] >= A[begin]) {
                // mid is on left (the higher) branch
                if (target < A[mid] && target >= A[begin])
                    end = mid;
                else
                    begin = mid;
            } else {
                // mid is on right (the lower) branch
                if (target > A[mid] && target <= A[end])
                    begin = mid;
                else
                    end = mid;
            }
            // The case where begin to end is sorted is covered by either of the above cases.
        }
        return -1;
    }
}
