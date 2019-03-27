package com.rainz;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
    public static void test(String args[]) {
        int[] input = {4,5,0,-2,-3,1};
        System.out.println(subarraySumsDivisibleByK(input, 5));
    }

    public static int subarraySumsDivisibleByK(int[] A, int K) {
        int sum = 0;
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(sum, 1);

        int result = 0;
        for (int x: A) {
            sum = (sum + x) % K;
            if (sum < 0)
                sum += K; // watch out for negative number
            Integer count = sums.get(sum);
            if (count != null) {
                result += count; // add count of all the sums matching "sum"
                sums.put(sum, count+1);
            }
            else {
                sums.put(sum, 1);
            }
        }
        return result;
    }
}
