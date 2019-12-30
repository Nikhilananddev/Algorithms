package com.rainz;

/*
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 */

public class MaximumProductSubarray {
    public static void test(String args[]) {
        int[] input1 = {2,3,-2,4};
        System.out.println(maxProduct(input1));
        int[] input2 = {-2,0,-1};
        System.out.println(maxProduct(input2));
        int[] input3 = {-4,-3,-2};
        System.out.println(maxProduct(input3));
    }

    /*
     * My solution breaks down arrays by 0's.
     * Within one group, if there are odd negatives, remove either first negative or last negative.
     * There is also a solution similar to max subarray sum. The difference is:
     * since you can have negatives, you have to keep both max (aka positive) and min (aka negative) and compare them.
     */
    public static int maxProduct(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int ans = 0; // if at least 2 nums, max subarray product is at least 0

        // Separate subarrays by 0's
        int start = -1;
        int prod = 0;
        int firstNegProd = 0;
        int lastNeg = -1;
        int negCount = 0;
        // Note the <= below
        for (int i = 0; i <= nums.length; ++i) {
            if (i == nums.length || nums[i] == 0) {
                if (i - start > 1 && negCount % 2 == 1) {
                    int lastNegProd = 1;
                    for (int j = lastNeg; j < i; ++j)
                        lastNegProd *= nums[j];
                    if (-lastNegProd > -firstNegProd)
                        prod /= firstNegProd; // keep last negative
                    else
                        prod /= lastNegProd; // keep first negative
                }
                if (prod > ans)
                    ans = prod;
                // Reset
                start = -1;
                prod = 0;
                firstNegProd = 0;
                lastNeg = -1;
                negCount = 0;
                continue;
            }
            if (start == -1) {
                start = i;
                prod = 1;
            }
            int n = nums[i];
            if (n < 0) {
                if (firstNegProd == 0)
                    firstNegProd = prod*n;
                lastNeg = i;
                ++negCount;
            }
            prod *= n;
        }
        return ans;
    }
}
