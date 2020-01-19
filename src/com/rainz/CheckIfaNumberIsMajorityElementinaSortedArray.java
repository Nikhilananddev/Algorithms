package com.rainz;

/*
 * Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.
 * A majority element is an element that appears more than N/2 times in an array of length N
 */

public class CheckIfaNumberIsMajorityElementinaSortedArray {
    public static void test(String args[]) {
        int[] input1 = {2,4,5,5,5,5,5,6,6};
        System.out.println(isMajorityElement(input1, 5));
        int[] input2 = {10,100,101,101};
        System.out.println(isMajorityElement(input2, 101));
    }
    public static boolean isMajorityElement(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        int N = nums.length;
        if (nums[N/2] != target)
            return false;
        // Search left
        int leftBound = N/2, rightBound = N/2;
        if (nums[0] == target)
            leftBound = 0;
        else {
            int lo = 1, hi = N / 2 - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] == target) {
                    leftBound = mid;
                    hi = mid - 1;
                } else
                    lo = mid + 1;
            }
        }
        if (nums[N-1] == target)
            rightBound = N - 1;
        else {
            int lo = N/2, hi = N - 2;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] == target) {
                    rightBound = mid;
                    lo = mid + 1;
                } else
                    hi = mid - 1;
            }
        }
        return rightBound - leftBound + 1 > N/2;
    }
}
