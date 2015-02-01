package com.rainz;

/**
 * Created by Yu on 2/1/2015.
 */
public class SearchInsertPosition {
    public static void test(String args[]) {
        int[] test = {1, 3, 5, 6};
        System.out.println(searchInsert(test, 5));
        System.out.println(searchInsert(test, 2));
        System.out.println(searchInsert(test, 7));
        System.out.println(searchInsert(test, 0));
    }

    public static int searchInsert(int[] A, int target) {
        int begin = 0, end = A.length - 1; // end is inclusive

        // Binary search for 'target'
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (begin == end) {
                return (target > A[begin] ? 1 : 0);
            }
            if (begin == end - 1) {
                // Two elements left.
                if (target <= A[begin])
                    return begin;
                if (target <= A[end])
                    return end;
                return end + 1;
            }
            if (A[mid] < target)
                begin = mid;
            else
                end = mid;
        }

        return 0; // if A is empty
    }
}
