package com.rainz;

/*
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4,5,6,7};
        rotate(input1, 3);
        Main.printArray(input1);
        int[] input2 = {-1,-100,3,99};
        rotate(input2, 2);
        Main.printArray(input2);
    }
    /*
     * Constant memory solution:
     * Take an element at idx, store it at idx+k, and save orig value there
     * Take the saved value, store it at idx+k+k, and save orig value there
     * Repeat the above will give you a cycle back to the idx you started
     * Note this only shifted elements whose idx % k == 0.
     * Now we start at idx+1 and repeat the above to shift another cycle.
     * Repeat until we have N elements shifted.
     */
    public static void rotate(int[] nums, int k) {
        int N = nums.length;
        k %= N;
        if (k == 0)
            return;
        int count = 0;
        for (int startIdx = 0; startIdx < N && count < N; ++startIdx) {
            int val = nums[startIdx], idx = startIdx;
            do {
                int dstIdx = (idx + k) % N;
                int tmp = nums[dstIdx];
                nums[dstIdx] = val;
                val = tmp;
                idx = dstIdx;
                ++count;
            } while (idx != startIdx);
        }
    }
}
