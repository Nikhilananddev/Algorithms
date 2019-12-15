package com.rainz;

public class SingleElementinaSortedArray {
    public static void test(String args[]) {
        int[] input1 = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(input1));
        int[] input2 = {3,3,7,7,10,11,11};
        System.out.println(singleNonDuplicate(input2));
        int[] input3 = {1,1,2};
        System.out.println(singleNonDuplicate(input3));
        int[] input4 = {0,1,1};
        System.out.println(singleNonDuplicate(input4));
    }

    public static int singleNonDuplicate(int[] nums) {
        // even odd, even odd, even, odd even, odd even, ...
        int lower = 0;
        int upper = nums.length-1;
        while (lower <= upper) {
            if (lower == upper)
                return nums[lower];
            int mid = (lower + upper) / 2;
            int even = mid / 2 * 2;
            if (nums[even] == nums[even+1]) {
                // The single number is after these two
                lower = even+2;
                continue;
            }
            if (even == 0 || nums[even] != nums[even-1])
                return nums[even]; // found
            // The single number is before these two
            upper = even-2;
        }
        return -1;
    }
}
