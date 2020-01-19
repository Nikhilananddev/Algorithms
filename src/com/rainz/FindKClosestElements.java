package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 */
public class FindKClosestElements {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4,5};
        System.out.println(findClosestElements(input1, 4, 3));
        int[] input2 = {1,2,3,4,5};
        System.out.println(findClosestElements(input2, 4, -1));
    }

    /*
     * Goal of this problem is to find the k-element window which includes k closes elements.
     * Imagine a window of size k sliding from left to right.
     * We name 1st element in window as "a", and 1st element after the window as "b".
     * If b is closer (in value) to x than a, then we slide right to include b and exclude a. See figure below:
     * -----[a------x--]b------  ==> -----a[------x--b]------
     * So we search for start of window via binary search.
     * If |x - arr[mid]| > |x - arr[mid+k]|, we slide right to include more elements on right. Else, we slide left.
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int N = arr.length;
        if (N == 0)
            return ans;
        // Search for start of the K-element window
        int lo = 0, hi = N - k - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (x - arr[mid] > arr[mid + k] - x)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        for (int i = 0; i < k; ++i)
            ans.add(arr[lo+i]);
        return ans;
    }

}
