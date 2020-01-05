package com.rainz;

public class SingleNumber {
    public static void test(String args[]) {
        int[] input1 = {2,2,1};
        System.out.println(singleNumber(input1));
        int[] input2 = {4,1,2,1,2};
        System.out.println(singleNumber(input2));
    }

    public static int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; ++i)
            ans ^= nums[i];
        return ans;
    }
}
