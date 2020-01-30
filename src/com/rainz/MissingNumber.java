package com.rainz;

/*
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 */
public class MissingNumber {
    public static void test(String args[]) {
        int[] input1 = {3,0,1};
        System.out.println(missingNumber(input1));
        int[] input2 = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(input2));
    }
    public static int missingNumber(int[] nums) {
        int sum = 0;
        for (int num: nums)
            sum += num;
        int N = nums.length;
        return (N+1)*N/2 - sum;
    }
}
