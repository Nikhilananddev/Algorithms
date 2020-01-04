package com.rainz;

import java.util.Random;

public class ShuffleanArray {
    public static void test(String args[]) {
        int[] input1 = {1,2,3};
        ShuffleanArray obj = new ShuffleanArray(input1);
        int[] param_1 = obj.reset();
        Main.printArray(param_1);
        int[] param_2 = obj.shuffle();
        Main.printArray(param_2);
    }

    private Random rand = new Random();
    private int[] original;

    public ShuffleanArray(int[] nums) {
        original = nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original.clone();
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] ans = original.clone();
        int N = ans.length;
        for (int i = 0; i < N; ++i) {
            int idx = rand.nextInt(N);
            int tmp = ans[idx];
            ans[idx] = ans[i];
            ans[i] = tmp;
        }
        return ans;
    }

}
