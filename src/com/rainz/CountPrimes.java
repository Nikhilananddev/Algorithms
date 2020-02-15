package com.rainz;

import java.util.Arrays;

/*
 * Count the number of prime numbers less than a non-negative number, n
 */
public class CountPrimes {
    public static void test(String args[]) {
        System.out.println(countPrimes(10));
        System.out.println(countPrimes(499979));
    }
    public static int countPrimes(int n) {
        if (n < 2)
            return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        int ans = 0;
        for (int i = 2; i*i < n; ++i) {
            if (isPrime[i]) {
                // Mark multiples of i not prime
                // Note: start from i*i instead of 2*i or 3*i because they were flagged when i=2, 3, etc
                for (int j = i*i; j < n; j += i)
                    isPrime[j] = false;
            }
        }
        for (boolean p: isPrime)
            if (p)
                ++ans;
        return ans;
    }
}
