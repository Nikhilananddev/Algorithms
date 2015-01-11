package com.rainz;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yu on 1/11/2015.
 */
public class TwoSum {
    public static void test(String[] args) {
        int[] test1 = {9, 12, 1, 5, 21};
        int[] result = twoSum(test1, 17);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();

        for (int i = 0; i < numbers.length; ++i) {
            int num = numbers[i];
            Integer otherIdx = lookup.get(target - num);
            if (otherIdx != null) {
                int[] result = new int[2];
                result[0] = otherIdx + 1; // 1-based index
                result[1] = i + 1; // 1-based index
                return result;
            }
            lookup.put(num, i);
        }

        return null;
    }
}
