package com.rainz;

import java.util.Stack;

public class OneThreeTwoPattern {
    public static void test(String args[]) {
        int[] input1 = {1, 2, 3, 4};
        System.out.println(find132pattern(input1));
        int[] input2 = {3, 1, 4, 2};
        System.out.println(find132pattern(input2));
        int[] input3 = {-1, 3, 2, 0};
        System.out.println(find132pattern(input3));
    }

    public static boolean find132pattern(int[] nums) {
        // The smallest number ready to be the next "one"
        Integer x1 = null;
        // Store incomplete and non-overlapping "13" patterns
        // They are in decreasing order, top of element is the smallest
        // And x1 <= top of element
        Stack<int[]> patterns13 = new Stack<>();
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            if (patterns13.isEmpty() || patterns13.peek()[0] >= num) {
                // num is smaller than the smallest stack item
                if (x1 == null || x1 >= num)
                    x1 = num;
                else {
                    // Save new "13" pattern
                    int[] p13 = {x1, num};
                    patterns13.push(p13);
                }
                continue;
            }
            // So num is not the smallest, it might:
            // - Fall within range of a "13", then we have a 132
            // - Fall between two "13"s, then we can replace all smaller ones with this new pattern.
            int[] p13 = {x1, num};
            while (!patterns13.isEmpty()) {
                int[] pTop = patterns13.peek();
                if (num > pTop[0] && num < pTop[1])
                    return true; // found "132"
                if (num <= pTop[0]) {
                    // Save new "13" pattern
                    break;
                }
                // Now num >= pTop[1], we should replace this with [x1, num] since it has a wider range
                patterns13.pop();
            }
            patterns13.push(p13);
        }
        return false;
    }
}
