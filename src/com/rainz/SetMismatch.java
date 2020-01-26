package com.rainz;

/*
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.
 */
public class SetMismatch {
    public static void test(String args[]) {
//        int[] input1 = {1,2,2,4};
//        Main.printArray(findErrorNums(input1));
        int[] input2 = {2,3,2};
        Main.printArray(findErrorNums(input2));
    }
    public static int[] findErrorNums(int[] nums) {
        int i = 0, N = nums.length;
        int dup = -1;
        while (i < N) {
            int n = nums[i];
            if (n == i + 1) {
                ++i;
                continue;
            }
            int tmp = nums[n-1];
            if (tmp == n) {
                dup = tmp;
                break;
            }
            nums[n-1] = n;
            nums[i] = tmp;
        }
        int sum = 0;
        for (int n: nums)
            sum += n;
        sum -= dup;
        int missing = (1+N)*N/2 - sum;
        int[] ans = {dup, missing};
        return ans;
    }
}
