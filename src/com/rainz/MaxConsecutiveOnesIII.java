package com.rainz;

public class MaxConsecutiveOnesIII {
    public static void test(String args[]) {
        int[] input = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(maxConsecutiveOnesIII(input, 2));

        int[] input2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println(maxConsecutiveOnesIII(input2, 3));
    }

    public static int maxConsecutiveOnesIII(int[] A, int K) {
        if (A.length == 0)
            return 0;

        int maxWin = 0;
        int start = 0;
        int end = 0; // exclusive
        int remain0 = K;
        while (end <= A.length) {
            if (end < A.length && A[end] == 1)
                ++end; // expand to include the next 1
            else if (end < A.length && remain0 > 0) {
                ++end; // flip the next 0 to 1 and expand window
                --remain0; // used one flip
            } else {
                // Unable to expand
                if (start < end) {
                    if (A[start] == 0)
                        ++remain0; // removed first 0
                    ++start; // shrink window
                } else {
                    // Move empty window forward
                    ++end;
                    ++start;
                }
            }
            int win = end - start;
            if (win > maxWin)
                maxWin = win;
        }
        return maxWin;
    }
}
