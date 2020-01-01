package com.rainz;

/*
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
 */
public class MinimumMovestoEqualArrayElements {
    public static void test(String args[]) {
        int[] input = {1,2,3};
        System.out.println(minMoves(input));
    }

    /* Incrementing all but one number is equivalent to decrementing that one number.
     * So we just need to find the smallest number, and decrement all others to match it.
     */
    public static int minMoves(int[] nums) {
        int smallest = Integer.MAX_VALUE;
        for (int n: nums)
            if (n < smallest)
                smallest = n;
        int ans = 0;
        for (int n: nums)
            ans += n - smallest;
        return ans;
    }
}
