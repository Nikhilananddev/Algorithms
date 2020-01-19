package com.rainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Note: The algorithm should run in linear time and in O(1) space.
 */

public class MajorityElementII {
    public static void test(String args[]) {
        int[] input1 = {3,2,3};
        System.out.println(majorityElement(input1));
        int[] input2 = {1,1,1,3,3,2,2,2};
        System.out.println(majorityElement(input2));
    }

    /*
     * When we see 3 different numbers, cancel them out.
     * Remaining two numbers are candidates. Then we just count them to see if they are.
     * Linear time, constant space.
     */
    public static List<Integer> majorityElement(int[] nums) {
        final int M = 2; // max # of numbers in the answer
        int m1 = 0, m2 = 0, m1Count = 0, m2Count = 0;
        int canceled = 0;
        for (int num: nums) {
            if (m1Count > 0 && m1 == num)
                ++m1Count;
            else if (m2Count > 0 && m2 == num)
                ++m2Count;
            else if (m1Count == 0) {
                m1 = num;
                m1Count = 1;
            } else if (m2Count == 0) {
                m2 = num;
                m2Count = 1;
            } else {
                // Both m1 & m2 exists, and num is different from both
                // Cancel out m1 and m2
                --m1Count;
                --m2Count;
            }
        }
        List<Integer> ans = new ArrayList<>();
        // Now count m1 and m2 to see if they are indeed majorities.
        m1Count = 0;
        m2Count = 0;
        for (int n: nums) {
            if (n == m1)
                ++m1Count;
            else if (n == m2)
                ++m2Count;
        }
        if (m1Count > nums.length/3)
            ans.add(m1);
        if (m2Count > nums.length/3)
            ans.add(m2);
        return ans;
    }

}
