package com.rainz;

/*
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] != nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that nums[-1] = nums[n] = -infinity.
 */
public class FindPeakElement {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,1};
        System.out.println(findPeakElement(input1));
        int[] input2 = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement(input2));
    }

    public static int findPeakElement(int[] nums) {
        int N = nums.length;
        if (N == 0)
            return -1;
        int lower = 0, upper = N-1;
        while (lower < upper) {
            int mid = (lower + upper) / 2;
            if (nums[mid] < nums[mid+1])
                lower = mid + 1; // on increasing slope
            else
                upper = mid; // on decreasing slope
        }
        return lower;
    }

}
