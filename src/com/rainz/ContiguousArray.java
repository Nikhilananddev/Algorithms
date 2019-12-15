package com.rainz;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public static void test(String args[]) {
        int[] input1 = {0,1};
        System.out.println(findMaxLength(input1));
        int[] input2 = {0,1,0};
        System.out.println(findMaxLength(input2));
    }

    public static int findMaxLength(int[] nums) {
        int score = 0;
        int maxLen = 0;
        Map<Integer, Integer> scoreMap = new HashMap<>();
        scoreMap.put(0, -1); // used if entire array has equal 0s and 1s
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1)
                ++score;
            else
                --score;
            Integer idx = scoreMap.get(score);
            if (idx == null)
                scoreMap.put(score, i);
            else {
                int len = i - idx;
                if (len > maxLen)
                    maxLen = len;
            }
        }

        return maxLen;
    }
}
