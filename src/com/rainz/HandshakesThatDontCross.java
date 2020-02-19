package com.rainz;

/*
 * You are given an even number of people num_people that stand around a circle and each person shakes hands with someone else, so that there are num_people / 2 handshakes total.
 * Return the number of ways these handshakes could occur such that none of the handshakes cross.
 * Since this number could be very big, return the answer mod 10^9 + 7
 */
public class HandshakesThatDontCross {
    public static void test(String args[]) {
        System.out.println(numberOfWays(2));
        System.out.println(numberOfWays(4));
        System.out.println(numberOfWays(6));
        System.out.println(numberOfWays(8));
    }
    public static int numberOfWays(int num_people) {
        long[] dp = new long[num_people+1];
        dp[0] = 1;
        dp[2] = 1;
        for (int numPpl = 4; numPpl <= num_people; numPpl += 2) {
            for (int j = 2; j <= numPpl; j += 2) {
                int jTo1 = j - 2;
                int other = numPpl - 2 - jTo1;
                dp[numPpl] += dp[jTo1] * dp[other];
                dp[numPpl] %= 1000000007;
            }
        }
        return (int)dp[num_people];
    }
}
