package com.rainz;

import java.util.ArrayList;

/**
 * Created by Yu on 1/30/2015.
 */
public class PermutationSequence {
    public static void test(String args[]) {
        int n = 3;
        int factorial = 1;
        for (int i = 2; i <= n; ++i)
            factorial *= i;
        for (int k = 1; k <= factorial; ++k)
            System.out.println(getPermutation(n, k));

    }

    public static String getPermutation(int n, int k) {
        // Compute factorials up to 9
        int[] factorials = new int[10];
        factorials[0] = 1;
        for (int i = 1; i < factorials.length; ++i)
            factorials[i] = i * factorials[i-1];
        // Build a list of all elements
        ArrayList<Character> remain = new ArrayList<Character>();
        for (int i = 1; i <= n; ++i)
            remain.add((char)('0' + i));
        // mod k with total # permutations, in case k is too big
        k = (k - 1) % factorials[n] + 1;

        char[] result = new char[n];
        for (int i = 0; i < n; ++i) {
            int factorial = factorials[n - 1 - i];
            int idx = (k - 1) / factorial;
            result[i] = remain.get(idx);
            remain.remove(new Character(result[i])); // remove the used digit
            k -= factorial * idx;
        }

        return new String(result);
    }
}
