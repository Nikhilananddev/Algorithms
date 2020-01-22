package com.rainz;

/*
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 */
public class MoveZeroes {
    public static void test(String args[]) {
        int[] input1 = {0,1,0,3,12};
        moveZeroes(input1);
        Main.printArray(input1);
    }
    public static void moveZeroes(int[] nums) {
        int rPtr = 0, wPtr = 0;
        while (rPtr < nums.length) {
            if (nums[rPtr] != 0)
                nums[wPtr++] = nums[rPtr];
            ++rPtr;
        }
        while (wPtr < nums.length)
            nums[wPtr++] = 0;
    }
}
