package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class JumpGame {
    public static void test(String args[]) {
        int[] test = {2,3,1,1,4};
        System.out.println(canJump(test));
        int[] test2 = {3,2,1,0,4};
        System.out.println(canJump(test2));
    }

    public static boolean canJump(int[] A) {
        if (A.length == 0)
            return true;
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
                maxIdx = newMax;
            }
            ++i;
        }
        return (i == A.length-1);
    }
}
