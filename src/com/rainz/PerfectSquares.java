package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerfectSquares {
    public static void test(String args[]) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
    }

    public static int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i*i <= n; ++i) {
            squares.add(i*i);
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int num = 2; num <= n; ++num) {
            int min = Integer.MAX_VALUE;
            for (int sq: squares) {
                if (num < sq)
                    break;
                int newCount = dp[num-sq] + 1;
                if (newCount < min)
                    min = newCount;
            }
            dp[num] = min;
        }

        return dp[n];
    }
}
