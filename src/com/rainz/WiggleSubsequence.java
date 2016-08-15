package com.rainz;

public class WiggleSubsequence {
    public static void test(String args[]) {
    	int[] nums = {1,17,5,10,13,15,10,5,16,8};
        System.out.println(wiggleMaxLength(nums));
    }
    
    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        // Deal with edge cases.
        if (n < 2) {
            return n;
        }
        int count = 1; // first number is always counted.
        int trend = 0; // 0=equal, 1=increasing, -1=decreasing
        boolean allEqual = true;
        for (int i = 1; i < n; ++i) {
            int diff = nums[i] - nums[i-1];
            if (diff > 0) {
                diff = 1; // increasing
                allEqual = false;
            } else if (diff < 0) {
                diff = -1; // decreasing
                allEqual = false;
            }
            if (trend != 0 && diff != 0 && trend != diff) {
                // The trend has been broken. The previous point is a wiggle point
                ++count;
            }
            // Update current trend if it is not "equal"
            if (diff != 0) {
                trend = diff;
            }
        }
        // Add the last point, unless all numbers are equal.
        if (!allEqual) {
            ++count; 
        }
        return count;
    }

}
