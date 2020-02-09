package com.rainz;

/*
 * You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.
 * Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.
 */

public class CircularArrayLoop {
    public static void test(String args[]) {
        int[] input1 = {2,-1,1,2,2};
        System.out.println(circularArrayLoop(input1));
        int[] input2 = {-1,2};
        System.out.println(circularArrayLoop(input2));
        int[] input3 = {-2,1,-1,-2,-2};
        System.out.println(circularArrayLoop(input3));
        int[] input4 = {1,2,3,4,5};
        System.out.println(circularArrayLoop(input4));
        int[] input5 = {-2,-3,-9};
        System.out.println(circularArrayLoop(input5));
    }

    public static boolean circularArrayLoop(int[] nums) {
        int N = nums.length;
        if (N == 0)
            return false;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; ++i) {
            if (visited[i])
                continue;
            visited[i] = true;
            int n = nums[i];
            boolean forward = n > 0;
            boolean[] inPath = new boolean[N];
            int curr = i;
            while (true) {
                if (inPath[curr])
                    return true;
                int currNum = nums[curr];
                int next = ((curr + currNum) % N + N) % N;
                if (curr == next)
                    break; // loop must be longer than 1
                boolean currFwd = currNum > 0;
                if (currFwd != forward)
                    break; // must move in same direction
                inPath[curr] = true;
                curr = next;
            }
        }
        return false;
    }
}
