package com.rainz;
/*
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 * You need to find the shortest such subarray and output its length.
 */
public class ShortestUnsortedContinuousSubarray {
    public static void test(String args[]) {
        int[] input1 = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(input1));
        int[] input2 = {1,2,3,4,5};
        System.out.println(findUnsortedSubarray(input2));
        int[] input3 = {1,3,2,4,5};
        System.out.println(findUnsortedSubarray(input3));
        int[] input4 = {5,4,3,2,1};
        System.out.println(findUnsortedSubarray(input4));
        int[] input5 = {1,3,2,2,2};
        System.out.println(findUnsortedSubarray(input5));
        int[] input6 = {3,3,3,1,2};
        System.out.println(findUnsortedSubarray(input6));
    }

    public static int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2)
            return 0;
        // Find in order part from left
        int leftIdx;
        for (leftIdx = 0; leftIdx < nums.length-1; ++leftIdx) {
            if (nums[leftIdx] > nums[leftIdx+1])
                break;
        }
        if (leftIdx == nums.length-1)
            return 0; // everything in order
        // Find in order part from right
        int rightIdx;
        for (rightIdx = nums.length-1; rightIdx > 0; --rightIdx) {
            if (nums[rightIdx] < nums[rightIdx-1])
                break;
        }
        // Find min and max within [leftIdx, rightIdx]
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = leftIdx; i <= rightIdx; ++i) {
            int n = nums[i];
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }
        // Using min & max, find begin and end in the left/right in order parts
        int begin, end;
        for (begin = leftIdx-1; begin >= 0; --begin)
            if (nums[begin] <= min)
                break;
        for (end = rightIdx+1; end < nums.length; ++end)
            if (nums[end] >= max)
                break;
        return end - begin - 1;
    }
}
