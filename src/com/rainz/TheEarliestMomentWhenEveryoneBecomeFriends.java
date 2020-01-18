package com.rainz;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/*
 * In a social group, there are N people, with unique integer ids from 0 to N-1.
 * We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.
 * Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.
 * Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.
 * Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.
 * 2 <= N <= 100
 * 1 <= logs.length <= 10^4
 * 0 <= logs[i][0] <= 10^9
 * 0 <= logs[i][1], logs[i][2] <= N - 1
 * It's guaranteed that all timestamps in logs[i][0] are different.
 * logs are not necessarily ordered by some criteria.
 * logs[i][1] != logs[i][2]
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {
    public static void test(String args[]) {
        int[][] input1 = {{20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},
                          {20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}};
        System.out.println(earliestAcq(input1, 6));
    }

    // Return root and set parents of nodes along the way to root
    private static int findRootPathCompression(int[] parents, int n) {
        Stack<Integer> stk = new Stack<>();
        while (n != parents[n]) {
            stk.push(n);
            n = parents[n];
        }
        // Now n is the root
        while (!stk.isEmpty()) {
            parents[stk.pop()] = n;
        }
        return n;
    }

    public static int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, Comparator.comparingInt(x->x[0]));
        int[] parents = new int[N];
        for (int i = 0; i < N; ++i)
            parents[i] = i; // root points parent at self
        int numRoots = N;
        for (int[] log: logs) {
            int ts = log[0];
            int p = log[1];
            int n = log[2];
            int pRoot = findRootPathCompression(parents, p);
            int nRoot = findRootPathCompression(parents, n);
            if (pRoot == nRoot)
                continue; // already connected
            parents[nRoot] = pRoot;
            --numRoots;
            if (numRoots == 1)
                return ts;
        }
        return -1;
    }

}
