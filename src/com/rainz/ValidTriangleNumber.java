package com.rainz;

import java.util.Arrays;

public class ValidTriangleNumber {
    public static void test(String args[]) {
        int[] input1 = {2,2,3,4};
        System.out.println(triangleNumber(input1));
    }

    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                int sideSum = nums[i] + nums[j];
                int idx = -1;
                int upper = nums.length - 1;
                int lower = j + 1;
                while (lower <= upper) {
                    int mid = (lower + upper) / 2;
                    if (nums[mid] < sideSum) {
                        idx = mid;
                        lower = mid + 1;
                    } else {
                        upper = mid - 1;
                    }
                }
                if (idx >= 0) {
                    ans += idx - j;
                }
            }
        }
        return ans;
    }
}
