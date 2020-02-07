package com.rainz;

/*
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 */

public class MaxConsecutiveOnesII {
    public static void test(String args[]) {
        int[] input = {1,0,1,1,0};
        System.out.println(findMaxConsecutiveOnes(input));
    }
    public static int findMaxConsecutiveOnes(int[] nums) {
        int N = nums.length;
        // Note the special case here:
        // If nums contains all 0's, at least we can flip one 0 to 1
        int ans = N == 0 ? 0 : 1;

        int start = -1;
        int[] lastBlock = {-1, -1}; // [start, end)
        for (int i = 0; i <= N; ++i) {
            int n = i < N ? nums[i] : 0;
            if (n == 1) {
                if (start < 0)
                    start = i;
            } else {
                if (start >= 0) {
                    // end of a block
                    int len;
                    if (lastBlock[0] >= 0) {
                        // Previous 1-block is separated by just a single 0
                        // So join the two blocks and the 0
                        len = 1 + (lastBlock[1] - lastBlock[0]) + (i - start);
                    } else {
                        len = i - start;
                        if (start > 0 || i < N)
                            ++len; // either start or end of this block has 0, so count it
                    }
                    if (len > ans)
                        ans = len;
                    lastBlock[0] = start;
                    lastBlock[1] = i;
                    start = -1;
                } else {
                    // at least two 0's between blocks, so set lastBlock to invalid
                    lastBlock[0] = -1;
                    lastBlock[1] = -1;
                }
            }
        }
        return ans;
    }
}
