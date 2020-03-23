package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/*
 * Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.
 * The returned array must be in sorted order.
 * Expected time complexity: O(n)
 */
public class SortTransformedArray {
    public static void test(String args[]) {
        int[] input1 = {-4,-2,2,4};
        Main.printArray(sortTransformedArray(input1, 1, 3, 5));
        int[] input2 = {-4,-2,2,4};
        Main.printArray(sortTransformedArray(input2, -1, 3, 5));
    }


    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();

        double mid = a != 0 ? -b/2.0/a : Double.MAX_VALUE;
        for (int x: nums) {
            int y = a*x*x+b*x+c;
            if (x < mid)
                part1.add(y);
            else
                part2.add(y);
        }
        if (a > 0 || (a == 0 && b < 0))
            Collections.reverse(part1);
        else if (a < 0)
            Collections.reverse(part2);

        int[] ans = new int[nums.length];
        int idx1 = 0, idx2 = 0, outIdx = 0;
        while (idx1 < part1.size() || idx2 < part2.size()) {
            if (idx1 >= part1.size())
                ans[outIdx++] = part2.get(idx2++);
            else if (idx2 >= part2.size())
                ans[outIdx++] = part1.get(idx1++);
            else {
                int y1 = part1.get(idx1);
                int y2 = part2.get(idx2);
                if (y1 <= y2) {
                    ans[outIdx++] = y1;
                    idx1++;
                } else {
                    ans[outIdx++] = y2;
                    idx2++;
                }
            }
        }
        return ans;
    }
}
