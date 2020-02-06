package com.rainz;

/*
 * This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
 * Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
 * How many distinct numbers can you dial in this manner?
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 */

import java.util.Arrays;

public class KnightDialer {
    public static void test(String args[]) {
        System.out.println(knightDialer(1));
        System.out.println(knightDialer(2));
        System.out.println(knightDialer(3));
    }

    public static int knightDialer(int N) {
        final int[][] dirs = new int[][] {{2,1}, {2, -1}, {-2,1}, {-2, -1},  {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        final int MODULO = 1000000007;
        int[][] dp = new int[4][3];
        for (int[] r: dp)
            Arrays.fill(r, 1);
        for (int hop = 1; hop < N; ++hop) {
            int[][] nextDp = new int[4][3];
            for (int r = 0; r < 4; ++r) {
                for (int c = 0; c < 3; ++c) {
                    nextDp[r][c] = 0;
                    for (int[] d: dirs) {
                        int newR = r + d[0];
                        int newC = c + d[1];
                        if (newR < 0 || newR >=4 || newC < 0 || newC >= 3 || (newR == 3 && newC !=1))
                            continue;
                        nextDp[r][c] = (nextDp[r][c] + dp[newR][newC]) % MODULO;
                    }
                }
            }
            dp = nextDp;
        }
        int ans = 0;
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 3; ++c) {
                if (r == 3 && c != 1)
                    continue;
                ans = (ans + dp[r][c]) % MODULO;
            }
        }
        return ans;
    }
}
