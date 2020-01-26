package com.rainz;

import java.util.Arrays;

/*
 * Students are asked to stand in non-decreasing order of heights for an annual photo.
 * Return the minimum number of students that must move in order for all students to be standing in non-decreasing order of height.
 */
public class HeightChecker {
    public static void test(String args[]) {
        int[] input1 = {1,1,4,2,1,3};
        System.out.println(heightChecker(input1));
    }
    public static int heightChecker(int[] heights) {
        int ans = 0;
        int[] sorted = heights.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < heights.length; ++i) {
            if (heights[i] != sorted[i])
                ++ans;
        }
        return ans;
    }
}
