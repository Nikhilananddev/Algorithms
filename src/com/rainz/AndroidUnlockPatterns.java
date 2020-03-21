package com.rainz;

/*
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 * Rules for a valid pattern:
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.
 */
public class AndroidUnlockPatterns {
    public static void test(String args[]) {
        System.out.println(numberOfPatterns(1, 1));
        System.out.println(numberOfPatterns(1, 2));
    }

    private static int helper(boolean[][] visited, int m, int n, int r, int c, int len) {
        int res = 0;
        if (visited[r][c] || len >= n)
            return 0;

        visited[r][c] = true;
        ++len;
        if (len >= m)
            ++res;

        for (int rr = 0; rr < 3; ++rr) {
            for (int cc = 0; cc < 3; ++cc) {
                int dr = Math.abs(rr - r);
                int dc = Math.abs(cc - c);
                if (dr == 0 && dc == 2 && !visited[r][1])
                    continue;
                if (dr == 2 && dc == 0 && !visited[1][c])
                    continue;
                if (dr == 2 && dc == 2 && !visited[1][1])
                    continue;
                res += helper(visited, m, n, rr, cc, len);
            }
        }
        visited[r][c] = false;
        return res;
    }

    public static int numberOfPatterns(int m, int n) {
        int ans = 0;
        boolean[][] visited = new boolean[3][3];
        ans += helper(visited, m, n, 0, 0, 0) * 4; // use symmetry
        ans += helper(visited, m, n, 0, 1, 0) * 4; // use symmetry
        ans += helper(visited, m, n, 1, 1, 0);
        return ans;
    }
}
