package com.rainz;

public class FindPivotIndex {
    public static void test(String args[]) {
        int[] input1 = {1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex(input1));
        int[] input2 = {1, 2, 3};
        System.out.println(pivotIndex(input2));
    }

    public static int pivotIndex(int[] nums) {
        if (nums.length == 0)
            return -1;
        int[] sums = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            sums[i] = sum;
        }
        int total = sums[nums.length-1];
        for (int i = 0; i < sums.length; ++i) {
            int leftSum = sums[i] - nums[i];
            int rightSum = total - nums[i] - leftSum;
            if (leftSum == rightSum)
                return i;
        }
        return -1;
    }
}
