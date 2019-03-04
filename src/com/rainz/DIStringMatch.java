package com.rainz;

import java.util.Arrays;

public class DIStringMatch {
    public static void test(String args[]) {
        System.out.println(Arrays.toString(diStringMatch("IDID")));
    }

    /*
        Here we use a strategy where we increment 1 to max for an 'I', and decrement 1 to min for a 'D'.
        Then we "re-base" the array values so the min is 0.
     */
    public static int[] diStringMatch(String S) {
        final int N = S.length() + 1;
        int[] vals = new int[N];
        vals[0] = 0;

        int min = 0;
        int max = 0;
        int writePtr = 1;
        for (int i = 0; i < S.length(); ++i) {
            char curr = S.charAt(i);
            if (curr == 'I') {
                ++max;
                vals[writePtr] = max;
            } else {
                --min;
                vals[writePtr] = min;
            }
            ++writePtr;
        }
        // Rebase all values so min starts from 0
        if (min < 0) {
            for (int i = 0; i < vals.length; ++i) {
                vals[i] -= min;
            }
        }
        return vals;
    }
}
