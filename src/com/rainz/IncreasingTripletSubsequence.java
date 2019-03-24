package com.rainz;

public class IncreasingTripletSubsequence {
    public static void test(String args[]) {
        int[] input = {1,2,3,4,5};
        System.out.println(increasingTripletSubsequence(input));
        int[] input2 = {5,4,3,2,1};
        System.out.println(increasingTripletSubsequence(input2));
    }

    public static boolean increasingTripletSubsequence(int[] nums) {
        if (nums.length < 3)
            return false;
        Integer[] seq = new Integer[2]; // candidate sequence with len=2
        Integer min = null; // candidate sequence with len=1, always less than seq[0]

        boolean result = false;
        seq[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int val = nums[i];
            if (val <= seq[0]) {
                // val can't be part of len=2 sequence, so check sequence with len=1
                if (min == null ||val < min) {
                    // We got a new/smaller sequence of len 1
                    min = val;
                } else if (val > min) {
                    // We got a smaller len-of-2 sequence {min, val}
                    seq[0] = min;
                    seq[1] = val;
                    min = null;
                }
            } else {
                // Now val > seq[0]
                if (seq[1] == null || val < seq[1])
                    seq[1] = val; // update 2nd number in sequence of len 2
                else if (val > seq[1])
                    return true; // found triplet {seq0, seq1, val}
            }
        }

        return result;
    }
}
