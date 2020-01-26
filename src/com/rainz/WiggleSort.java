package com.rainz;

/*
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 */

public class WiggleSort {
    public static void test(String args[]) {
        int[] input1 = {3,5,2,1,6,4};
        wiggleSort(input1);
        Main.printArray(input1);
    }

    public static void wiggleSort(int[] nums) {
        int N = nums.length;
        if (N < 2)
            return;
        boolean up = true;
        for (int i = 1; i < N; ++i) {
            if (up) {
                if (nums[i] < nums[i-1]) {
                    int tmp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = tmp;
                }
            } else {
                if (nums[i] > nums[i-1]) {
                    int tmp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = tmp;
                }
            }
            up = !up;
        }
    }
}
