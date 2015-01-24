package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 1/24/2015.
 */
public class ThreeSumClosest {
    public static void test(String args[]) {
        int[] test = {0, 2, 1,-3};
        System.out.println(threeSumClosest(test, 1));
    }

    public static int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int closest = 0, minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < num.length - 2; ++i) {
            int curr = num[i];
            if (i > 0 && curr == num[i-1]) {
                continue;
            }
            int leftIdx = i + 1;
            int rightIdx = num.length - 1;
            int sum2Target = target - curr;
            while (leftIdx < rightIdx) {
                int sum2 = num[leftIdx] + num[rightIdx];
                if (sum2 == sum2Target) {
                    return target; // found exact match
                }
                int diff = Math.abs(sum2 - sum2Target);
                if (diff < minDiff) {
                    minDiff = diff;
                    closest = curr + sum2;
                }
                if (sum2 < sum2Target) {
                    ++leftIdx;
                } else {
                    --rightIdx;
                }
            }
        }
        return closest;
    }
}
