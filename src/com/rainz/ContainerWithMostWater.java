package com.rainz;

/**
 * Created by Yu on 1/21/2015.
 */
public class ContainerWithMostWater {
    public static void test(String args[]) {
        int[] test = {1, 5, 7, 4, 3, 1};
        System.out.println(maxArea(test));
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            int hLeft = height[left], hRight = height[right];
            int area = 0;
            if (hLeft < hRight) {
                area = hLeft * (right - left);
                ++left;
            } else {
                area = hRight * (right - left);
                --right;
            }
            if (result < area)
                result = area;
        }

        return result;
    }
}
