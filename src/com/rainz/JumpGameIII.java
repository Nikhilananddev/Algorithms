package com.rainz;

/*
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * Notice that you can not jump outside of the array at any time.
 */

public class JumpGameIII {
    public static void test(String args[]) {
        int[] input1 = {4,2,3,0,3,1,2};
        System.out.println(canReach(input1, 5));
        int[] input2 = {4,2,3,0,3,1,2};
        System.out.println(canReach(input2, 0));
        int[] input3 = {3,0,2,1,2};
        System.out.println(canReach(input3, 2));
    }

    public static boolean canReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length)
            return false;
        if (arr[start] == -1)
            return false;
        if (arr[start] == 0)
            return true;
        int idx1 = start + arr[start];
        int idx2 = start - arr[start];
        int sav = arr[start];
        arr[start] = -1;
        if (canReach(arr, idx1))
            return true;
        if (canReach(arr, idx2))
            return true;
        arr[start] = sav;
        return false;
    }
}
