package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 */

public class SummaryRanges {
    public static void test(String args[]) {
        int[] input1 = {0,1,2,4,5,7};
        Main.printList(summaryRanges(input1));
        int[] input2 = {0,2,3,4,6,8,9};
        Main.printList(summaryRanges(input2));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 0)
            return ans;
        int start = 0;
        for (int i = 1; i <= nums.length; ++i) {
            if (i == nums.length || nums[i] != nums[i-1]+1) {
                int numStart = nums[start];
                int numEnd = nums[i-1];
                if (start != i - 1)
                    ans.add(String.format("%d->%d", numStart, numEnd));
                else
                    ans.add(""+numStart);
                start = i;
            }
        }
        return ans;
    }
}
