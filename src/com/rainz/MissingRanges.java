package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 */
public class MissingRanges {
    public static void test(String args[]) {
        int[] input1 = {0, 1, 3, 50, 75};
        System.out.println(findMissingRanges(input1, 0, 99));
        int[] input2 = {-2147483648,2147483647};
        System.out.println(findMissingRanges(input2, -2147483648, 2147483647));
    }

    private static String buildRangeStr(int lo, int hi) {
        StringBuilder sb = new StringBuilder();
        sb.append(lo);
        if (hi > lo) {
            sb.append("->");
            sb.append(hi);
        }
        return sb.toString();
    }
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int idx = 0, curr = lower;
        List<String> ans = new ArrayList<>();
        int N = nums.length;
        if (N == 0) {
            ans.add(buildRangeStr(lower, upper));
            return ans;
        }
        if (lower < nums[0]) {
            ans.add(buildRangeStr(lower, nums[0]-1));
        }
        for (int i = 1; i < N; ++i) {
            if (nums[i] <= lower)
                continue;
            if ((long)nums[i] - (long)nums[i-1] > 1) {
                ans.add(buildRangeStr(nums[i-1]+1, nums[i]-1));
            }
            if (nums[i] >= upper)
                break;
        }
        if (nums[N-1] < upper)
            ans.add(buildRangeStr(nums[N-1]+1, upper));

        return ans;
    }
}
