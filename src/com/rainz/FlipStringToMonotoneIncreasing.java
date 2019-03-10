package com.rainz;

public class FlipStringToMonotoneIncreasing {
    public static void test(String args[]) {
        System.out.println(flipStringToMonotoneIncreasing("00110"));
        System.out.println(flipStringToMonotoneIncreasing("010110"));
        System.out.println(flipStringToMonotoneIncreasing("00011000"));
    }

    public static int flipStringToMonotoneIncreasing(String S) {
        int num0 = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '0')
                ++num0;
        }

        int minFlips = Integer.MAX_VALUE;
        int flips = 0;
        int left0 = num0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '0') {
                --left0;
                continue;
            }
            // Now we see a '1'
            // Case 1: don't flip, then we must flip all 0's after it
            int totalFlips = flips + left0;
            if (totalFlips < minFlips)
                minFlips = totalFlips;
            // Case 2: flip 1 to 0
            ++flips;
        }
        if (flips < minFlips)
            minFlips = flips;

        return minFlips;
    }
}
