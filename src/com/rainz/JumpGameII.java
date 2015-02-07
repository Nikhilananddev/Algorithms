package com.rainz;

/**
 * Created by Yu on 2/7/2015.
 */
public class JumpGameII {
    public static void test(String args[]) {
        int[] test = {2,3,1,1,4};
        System.out.println(jump(test));
    }

    public static int jump(int[] A) {
        if (A.length == 0)
            return 0;
        int steps = 0;
        int i = 0;
        int newMax = 0, maxIdx = 0;
        while (i <= maxIdx) {
            int nextStop = Math.min(A.length - 1, i + A[i]);
            if (nextStop > newMax) {
                newMax = nextStop;
            }
            if (i == maxIdx) {
                if (newMax <= maxIdx)
                    break;
                ++steps;
                maxIdx = newMax;
            }
            ++i;
        }
        if (i == A.length-1)
            return steps;
        return -1; // can't reach the end
    }
}
