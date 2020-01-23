package com.rainz;

/*
 * Imagine you have a special keyboard with the following keys:
 * Key 1: (A): Print one 'A' on screen.
 * Key 2: (Ctrl-A): Select the whole screen.
 * Key 3: (Ctrl-C): Copy selection to buffer.
 * Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
 * Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.
 */
public class FourKeysKeyboard {
    public static void test(String args[]) {
        System.out.println(maxA(3));
        System.out.println(maxA(7));
    }
    public static int maxA(int N) {
        int[] dp = new int[N+1];
        for (int i = 1; i <= N; ++i) {
            dp[i] = i;
            for (int j = 1; j <= i - 3; ++j) {
                // i-j-2 gives you # ctrl-v's, then we add the initial j keys
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 2) + dp[j]);
            }
        }
        return dp[N];
    }

}
