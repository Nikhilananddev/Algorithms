package com.rainz;

import java.util.Arrays;

/*
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 */
public class ThreeSumSmaller {
    public static void test(String args[]) {
        int[] input1 = {-2,0,1,3};
        System.out.println(threeSumSmaller(input1, 2));
    }

    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            int j = i + 1, k = nums.length - 1;
            int numI = nums[i];
            while (j < k) {
                int numJ = nums[j], numK = nums[k];
                if (numI + numJ + numK >= target)
                    --k;
                else {
                    ans += k - j;
                    ++j;
                }
            }
        }
        return ans;
    }

}
