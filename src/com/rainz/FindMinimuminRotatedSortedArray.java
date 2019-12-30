package com.rainz;

public class FindMinimuminRotatedSortedArray {
    public static void test(String args[]) {
        int[] input1 = {3,4,5,1,2};
        System.out.println(findMin(input1));
        int[] input2 = {4,5,6,7,0,1,2};
        System.out.println(findMin(input2));
        int[] input3 = {1};
        System.out.println(findMin(input3));
        int[] input4 = {2,1};
        System.out.println(findMin(input4));
        int[] input5 = {3,1,2};
        System.out.println(findMin(input5));
    }

    public static int findMin(int[] nums) {
        int lower = 0;
        int upper = nums.length - 1;
        while (lower <= upper) {
            // "<=" below is necessary to handle lower==upper
            if (nums[lower] <= nums[upper]) {
                // We are on the same branch
                return nums[lower];
            }
            int mid = (lower + upper) / 2;
            // ">=" below is necessary to handle mid==lower
            if (nums[mid] >= nums[lower]) {
                // mid is on the left (higher) branch
                lower = mid + 1;
            } else {
                // mid is on the right (lower) branch
                upper = mid; // must include mid, it might be the right answer
            }
        }
        return -1; // shouldn't get here
    }
}
