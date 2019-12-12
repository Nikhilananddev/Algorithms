package com.rainz;

public class GreatestSumDivisibleByThree {
    public static void test(String args[]) {
        int[] input = {3, 6, 5, 1, 8};
        System.out.println(solve(input));
    }

    public static int solve(int[] nums) {
        // modx[0]: smallest, modx[1]: 2nd smallest, 0 means invalid
        int[] mod1 = {0, 0};
        int[] mod2 = {0, 0};

        int sum = 0;
        for (int n : nums) {
            sum += n;
            int r = n % 3;
            if (r == 0)
                continue;
            int[] modx = r == 1 ? mod1 : mod2;
            if (modx[0] == 0 || n <= modx[0]) {
                // smallest
                modx[1] = modx[0];
                modx[0] = n;
            } else if (modx[1] == 0 || n < modx[1]) {
                // 2nd smallest
                modx[1] = n;
            }
        }

        int r = sum % 3;
        int sumMod1 = mod1[0] + mod1[1]; // if both exists, % 3 = 2
        int sumMod2 = mod2[0] + mod2[1]; // if both exists, % 3 = 1
        int removeSum = 0;
        if (r == 1) {
            if (sumMod2 % 3 != 1) {
                // don't have 2 mod2's, so set this to invalid
                sumMod2 = Integer.MAX_VALUE;
            }
            if (mod1[0] == 0) {
                // don't have a mod1, so set this to invalid
                mod1[0] = Integer.MAX_VALUE;
            }
            // Choose between removing a mod1 or two mod2's
            removeSum = sumMod2 < mod1[0] ? sumMod2 : mod1[0];
        } else if (r == 2) {
            if (sumMod1 % 3 != 2) {
                // don't have 2 mod1's, so set this to invalid
                sumMod1 = Integer.MAX_VALUE;
            }
            if (mod2[0] == 0) {
                // don't have a mod2, so set this to invalid
                mod2[0] = Integer.MAX_VALUE;
            }
            // Choose between removing a mod2 or two mod1's
            removeSum = sumMod1 < mod2[0] ? sumMod1 : mod2[0];
        }

        return sum - removeSum;
    }
}